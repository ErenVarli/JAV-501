package game.garfield.character.enemy;

import java.util.List;

import game.garfield.AEntity;
import game.garfield.character.Garfield.AGarfield;
import game.garfield.power.APower;
import game.garfield.power.powerEnemy.FirePowerEnemy;
import javafx.scene.paint.Color;

public class EnemyFire extends AEnemy {

    private static final int DELAY = 30;
    private static final int CHARGE = 30;
    private int load = CHARGE;
    private boolean attacking = false;

    public EnemyFire(float x, float y, float vectorX, float vectorY,List<AEntity> entities) {
        super(x, y, vectorX, vectorY, 3, "path", DELAY,entities);
        this.enemyType = enemyType.FIRE;
        this.setFill(Color.RED);
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
    public void update(AGarfield hero) {
        if(invFrame>0){
            setStroke(Color.BLACK);
        } else{
            setStroke(Color.TRANSPARENT);
        }
        lookingRight = getX() <= hero.getX();
        invFrame-=1;
        if (attacking && load < 1) {
            attack();
            load = CHARGE;
            attacking=false;
        } else if (attacking && !(load < 1)) {
            load -= 1;
        } else if (!attacking && delayAttack < 1) {
            delayAttack = DELAY;
            attacking = true;
        } else {
            delayAttack -= 1;
        }
    }

    @Override
    public APower attack() {
        //Animation 

        FirePowerEnemy projectile = new FirePowerEnemy(position.getpX(), position.getpY(), this,lookingRight);
        projectile.setLookingRight(lookingRight);
        if (!lookingRight) {
            projectile.getSpeedVector().setVectorIndex(0, projectile.getSpeedVector().getVectorIndex(0) + (-1));
        }
        entities.add(projectile);
        root.getChildren().add(projectile);
        projectile.setRoot(root);
        projectile.setEntities(entities);
        return projectile;
    }

}
