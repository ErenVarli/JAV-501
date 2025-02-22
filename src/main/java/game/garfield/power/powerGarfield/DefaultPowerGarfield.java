package game.garfield.power.powerGarfield;

import game.garfield.character.Garfield.AGarfield;
import game.garfield.character.enemy.AEnemy;
import game.garfield.power.powerEnemy.APowerEnemy;

public class DefaultPowerGarfield extends APowerGarfield{
    private final static String FILE1 = "file1";
    private final static String FILE2 = "file2";
    private final static String IMAGE_FILE="imgfile";
    private final static float SIZE_X = 10;
    private final static float SIZE_Y = 20;
    private final static int DAMAGE = 1;
    public DefaultPowerGarfield(float x, float y, float vecX, float vecY, AGarfield owner, boolean lookingRight){
        super(x-10, y, vecX, vecY, SIZE_X, SIZE_Y, DAMAGE, FILE1, FILE2, IMAGE_FILE, owner, 10, lookingRight);
        if(lookingRight){
            position.setpX(position.getpX()+30);
            setX(position.getpX());
        } 
    }
    
    /** 
     * powerDefault is a part of garfield(an arm that scratches), so if it touches an enemyPower it gets damaged;
     * @param powerEnemy
     */
    @Override
    public void touchesPower(APowerEnemy powerEnemy) {
        if(powerEnemy.attack((AGarfield)(this.owner))){
            
        }
    }

    @Override
    public void detectIntersection() {
        for(int i=entities.size()-1;i>=0;i--){
            if(this.getBoundsInParent().intersects(entities.get(i).getBoundsInParent())){
                if(entities.get(i) instanceof AEnemy){
                    this.attack((AEnemy)(entities.get(i)));
                } 
            }
        }
    }

    @Override
    public void update(AGarfield hero) {
        lifeTime -= 1;
        if(lifeTime<1){
            ((AGarfield) owner).getProjectiles().remove(this);
            death();
        }
        detectIntersection();
        if(lookingRight){
            setX(owner.getX()+20);
        } else{
            setX(owner.getX()-10);
        }
        setY(owner.getY());
    }
}
