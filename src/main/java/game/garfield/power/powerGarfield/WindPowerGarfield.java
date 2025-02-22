package game.garfield.power.powerGarfield;

import game.garfield.character.Garfield.AGarfield;
import game.garfield.character.enemy.AEnemy;
import game.garfield.character.enemy.EnemyFire;
import game.garfield.object.Bloc;
import game.garfield.power.powerEnemy.APowerEnemy;
import game.garfield.power.powerEnemy.RockPowerEnemy;
import javafx.scene.paint.Color;

public class WindPowerGarfield extends APowerGarfield {

    private final static String FILE1 = "file1";
    private final static String FILE2 = "file2";
    private final static String IMAGE_FILE = "imgfile";
    private final static float SIZE_X = 40;
    private final static float SIZE_Y = 40;
    private final static float VEC_X = 1;
    private final static float VEC_Y = 1;
    private final static int DAMAGE = 1;

    public WindPowerGarfield(float x, float y, float vecX, float vecY, AGarfield owner, boolean lookingRight) {
        super(x-40, y-23, vecX, vecY, SIZE_X, SIZE_Y, DAMAGE, FILE1, FILE2, IMAGE_FILE, owner, 90, lookingRight);
        this.setFill(Color.AQUAMARINE);
        if(lookingRight){
            position.setpX(position.getpX()+60);
            setX(position.getpX());
        }

    }

    /**
     * Slow down enemy for a certain amount of type and causes damage
     *
     * @param enemy getting interacted with
     * @return boolean => true: enemy still alive, false: enemy has no more hp
     */
    @Override
    public boolean attack(AEnemy enemy) {
        if (!(enemy instanceof EnemyFire)) {
            return enemy.isAttacked(this.damage,(AGarfield) owner);
        }
        return true;
    }

    /**
     * if powerEnemy is of type Rock, the rocks get thrown away//todo
     *
     * @param powerEnemy
     */
    @Override
    public void touchesPower(APowerEnemy powerEnemy) {
        if (powerEnemy instanceof RockPowerEnemy) {
            //powerEnemy.goToTheOtherWay();//to implement in enemy
        }
    }

    @Override
    public void update(AGarfield hero) {
        lifeTime -= 1;
        if(lookingRight){
            setX(getX()+3);
        } else{
            setX(getX()-3);
        }
        if(lifeTime<1){
            ((AGarfield) owner).getProjectiles().remove(this);
            death();
        }
        // detectXWindowLimit();
        // detectYWindowLimit();
        detectIntersection();
    }

    @Override
    public void detectIntersection() {
        for(int i=entities.size()-1;i>=0;i--){
            if(this.getBoundsInParent().intersects(entities.get(i).getBoundsInParent())){
                if(entities.get(i) instanceof AEnemy && !(entities.get(i) instanceof EnemyFire)){
                    this.attack((AEnemy)(entities.get(i)));
                }
                else if(entities.get(i) instanceof Bloc){
                    ((AGarfield) owner).getProjectiles().remove(this);
                    death();
                    return;
                } 
            }
        }
    }
}
