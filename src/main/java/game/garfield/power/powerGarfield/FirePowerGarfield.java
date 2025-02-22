package game.garfield.power.powerGarfield;

import game.garfield.character.Garfield.AGarfield;
import game.garfield.power.powerEnemy.APowerEnemy;
import game.garfield.power.powerEnemy.RockPowerEnemy;
import javafx.scene.paint.Color;

public class FirePowerGarfield extends APowerGarfield {

    private final static String FILE1 = "file1";
    private final static String FILE2 = "file2";
    private final static String IMAGE_FILE="imgfile";
    private final static float SIZE_X = 45;
    private final static float SIZE_Y = 30;
    private final static int DAMAGE = 1024;

    public FirePowerGarfield(float x, float y, float vecX, float vecY, AGarfield owner, boolean lookingRight) {
        super(x-45, y-13, vecX, vecY, SIZE_X, SIZE_Y, DAMAGE, FILE1, FILE2, IMAGE_FILE, owner, 15, lookingRight);
        this.setFill(Color.RED);
        if(lookingRight){
            position.setpX(position.getpX()+65);
            setX(position.getpX());
        }
    }

    /** 
     *   If powerEnemy is of type Rock, the damages from rockPower gets even higher
     * @param powerEnemy getting interacted with 
     */
    @Override
    public void touchesPower(APowerEnemy powerEnemy) {
        if(powerEnemy instanceof RockPowerEnemy){
            ((RockPowerEnemy) powerEnemy).setsFire();
        }
    }

    @Override
    public void update(AGarfield hero) {
        lifeTime -= 1;
        if(lookingRight){
            setX(getX()+2);
        } else{
            setX(getX()-2);
        }
        if(lifeTime<1){
            ((AGarfield) owner).getProjectiles().remove(this);
            death();
        }
        detectIntersection();
    }
}
