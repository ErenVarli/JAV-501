package game.garfield.power.powerEnemy;

import game.garfield.character.ACharacter;
import game.garfield.character.Garfield.AGarfield;
import game.garfield.character.Garfield.GarfieldRock;
import game.garfield.character.enemy.AEnemy;
import game.garfield.object.Bloc;
import game.garfield.power.APower;
import game.garfield.power.powerGarfield.RockPowerGarfield;
import game.garfield.power.powerGarfield.WindPowerGarfield;
import javafx.scene.paint.Color;

public class RockPowerEnemy extends APowerEnemy{
    private final static String FILE1 = "file1";
    private final static String FILE2 = "file2";
    private final static float SIZE_X=10;
    private final static float SIZE_Y=10;
    private final static float VEC_X=1;
    private final static float VEC_Y=1;
    private final static int DAMAGE=1;
    private final static int DAMAGE_ON_FIRE=10;
    
    public RockPowerEnemy(float x, float y, float vecX, float vecY, AEnemy owner, boolean lookingRight){
        super(x-10, y+3, VEC_X, VEC_Y, SIZE_X, SIZE_Y, DAMAGE, FILE1,FILE2, "imagePath", owner, 60,  lookingRight);
        this.setFill(Color.GRAY);
        if(lookingRight){
            position.setpX(position.getpX()+30);
            setX(position.getpX());
        }
    }
    @Override
    public boolean attack(AGarfield garfield) {
        death();
        return garfield.isAttacked(damage,owner);
    }

    public void setsFire() {
        this.damage=DAMAGE_ON_FIRE;
    }

    @Override
    public void update(AGarfield hero) {
        lifeTime -= 1;
        if(lookingRight){
            setX(getX()+10);
        } else{
            setX(getX()-10);
        }
        if(lifeTime<1){
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
                if (entities.get(i) instanceof WindPowerGarfield && (ACharacter) ((APower) entities.get(i)).getOwner()!=owner) {
                    owner=(ACharacter) ((APower) entities.get(i)).getOwner();
                    lookingRight=entities.get(i).getLookingRight();
                    ((AGarfield) owner).getProjectiles().add(this);
                } else if(entities.get(i) instanceof AGarfield  && !(entities.get(i) instanceof GarfieldRock && ((GarfieldRock) entities.get(i)).getAttacking())){
                    this.attack((AGarfield)(entities.get(i)));
                }
                else if(entities.get(i) instanceof Bloc || entities.get(i) instanceof RockPowerGarfield){
                    if(owner instanceof AGarfield){
                        ((AGarfield) owner).getProjectiles().remove(this);
                    }
                    death();
                    return;
                } else if(entities.get(i) instanceof AEnemy  && owner instanceof AGarfield){
                    death();
                    ((AGarfield) owner).getProjectiles().remove(this);
                    ((AEnemy) entities.get(i)).isAttacked(this.getDamage(),(AGarfield) owner);
                }
            }
        }
    }

}
