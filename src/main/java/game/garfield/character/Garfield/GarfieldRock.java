package game.garfield.character.Garfield;

import java.util.ArrayList;
import java.util.List;

import game.garfield.AEntity;
import game.garfield.App;
import game.garfield.power.APower;
import game.garfield.power.powerGarfield.RockPowerGarfield;
import game.garfield.util.Position;
import game.garfield.util.Vector;
import javafx.scene.paint.Color;

public class GarfieldRock extends AGarfield {
    private static final int DELAY = 15;
    private static final String FILE_PATH = "filepath";
    private RockPowerGarfield projectile;
    private static final float VX = 2;
    private static final float VY = 2;
    private boolean rock = false;
    private static final int CHARGE = 45;
    private int load = CHARGE;

    public GarfieldRock(Position p, int hp,List<AEntity> entities, App instance) {
        super(p.getpX(), p.getpY(), VX, VY, FILE_PATH,DELAY,entities, instance);
        this.setFill(Color.GRAY);
        this.setHp(hp);
    }

    @Override
    public Vector getSpeedVector() {
        if (this.rock) {
            List<Float> powerActiveVect = new ArrayList<>();
            powerActiveVect.add((float) 0);
            powerActiveVect.add(this.speedVector.getVectorIndex(1));
            return new Vector(powerActiveVect);
        }
        return super.getSpeedVector();
    }

    public boolean getAttacking(){
        return rock;
    }

    @Override
    public APower attack() {
        if(delayAttack<1){
            rock = true;
            projectile = new RockPowerGarfield(position.getpX(), position.getpY(), speedVector.getVectorIndex(0), speedVector.getVectorIndex(1), this,lookingRight);
            projectiles.add(projectile);
            entities.add(projectile);
            root.getChildren().add(projectile);
            projectile.setRoot(root);
            projectile.setEntities(entities);
        }
        return null;
    }

    private void finAttack(){

    }

    @Override
    public void update(AGarfield hero) {
        if( rock && load<1){
            finAttack();
            rock=false;
            load=CHARGE;
            delayAttack=DELAY;
        }else if (rock && !(load<1)) {
            load-=1;
        } else{
            delayAttack-=1;
        }
    }

}