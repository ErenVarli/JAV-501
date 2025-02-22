package game.garfield.power.powerGarfield;

import game.garfield.character.Garfield.AGarfield;
import game.garfield.character.enemy.AEnemy;
import game.garfield.power.powerEnemy.APowerEnemy;
import game.garfield.power.powerEnemy.RockPowerEnemy;
import javafx.scene.paint.Color;

public class RockPowerGarfield extends APowerGarfield {

    private final static String FILE1 = "file1";
    private final static String FILE2 = "file2";
    private final static String IMAGE_FILE="imgfile";
    private final static float SIZE_X = 42;
    private final static float SIZE_Y = 42;
    private final static int DAMAGE = 1024;

    public RockPowerGarfield(float x, float y, float vecX, float vecY, AGarfield owner, boolean lookingRight) {
        super(x-11, y-11, vecX, vecY, SIZE_X, SIZE_Y, DAMAGE, FILE1, FILE2, IMAGE_FILE, owner, 45, lookingRight);
        this.setFill(Color.GRAY);
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
        setX(owner.getX()-11);
        setY(owner.getY()-11);
        if(lifeTime<1){
            ((AGarfield) owner).getProjectiles().remove(this);
            death();
        }
        detectIntersection();
    }

    @Override
    public void detectIntersection() {
        for(int i=entities.size()-1;i>=0;i--){
            if(this.getBoundsInParent().intersects(entities.get(i).getBoundsInParent())){
                if(entities.get(i) instanceof AEnemy){
                    ((AGarfield) owner).getProjectiles().remove(this);
                    death();
                    this.attack((AEnemy)(entities.get(i)));
                }
            }
        }
    }

}
