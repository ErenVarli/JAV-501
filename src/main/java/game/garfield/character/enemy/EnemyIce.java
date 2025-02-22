package game.garfield.character.enemy;

import java.util.ArrayList;
import java.util.List;

import game.garfield.AEntity;
import game.garfield.character.Garfield.AGarfield;
import game.garfield.power.APower;
import game.garfield.power.powerEnemy.WindPowerEnemy;
import javafx.scene.paint.Color;
    
public class EnemyIce extends AEnemy{
    private static final int DELAY = 50;

    public EnemyIce(float x, float y, float vectorX, float vectorY,List<AEntity> entities){
        super( x,  y,  vectorX,  vectorY,  3, "path",DELAY,entities);
        this.fileAttack= new ArrayList<>();
        this.enemyType=enemyType.WIND;
        this.setFill(Color.AQUAMARINE);
    }
        
    /*public static List<String> loadJump(){
        List<String> fileJump = new ArrayList<>();
        fileJump.add("file1");
        fileJump.add("file2");
        return fileJump;
    }
    public static List<String> loadLeft(){
        List<String> fileLeft = new ArrayList<>();
        fileLeft.add("file1");
        fileLeft.add("file2");
        return fileLeft;
    }
    public static List<String> loadRight(){
        List<String> fileRight = new ArrayList<>();
        fileRight.add("file1");
        fileRight.add("file2");
        return fileRight;
    }*/
    
    @Override
    public APower attack(){
        //Animation
        WindPowerEnemy projectile = new WindPowerEnemy(position.getpX(),position.getpY(),speedVector.getVectorIndex(0),speedVector.getVectorIndex(1),this,lookingRight);
        projectile.setLookingRight(lookingRight);
        if(lookingRight){
            projectile.getSpeedVector().setVectorIndex(0, projectile.getSpeedVector().getVectorIndex(0)+1);
        } else {
            projectile.getSpeedVector().setVectorIndex(0, projectile.getSpeedVector().getVectorIndex(0)-1);
        }
        entities.add(projectile);
        root.getChildren().add(projectile);
        projectile.setRoot(root);
        projectile.setEntities(entities);
        return projectile;
    }

    @Override
    public void update(AGarfield hero) {
        if(invFrame>0){
            setStroke(Color.BLACK);
        } else{
            setStroke(Color.TRANSPARENT);
        }
        lookingRight = getX() >= hero.getX();
        invFrame-=1;
        delayAttack-=1;
        if(delayAttack<1){
            lookingRight = getX() <= hero.getX();
            delayAttack=DELAY;
            attack();
        } 
    }
}