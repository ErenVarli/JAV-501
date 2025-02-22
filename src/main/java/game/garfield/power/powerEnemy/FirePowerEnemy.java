package game.garfield.power.powerEnemy;

import game.garfield.character.Garfield.AGarfield;
import game.garfield.character.enemy.AEnemy;
import game.garfield.object.Bloc;
import game.garfield.power.powerGarfield.RockPowerGarfield;
import javafx.scene.paint.Color;

public class FirePowerEnemy extends APowerEnemy{
    private final static String FILE1 = "file1";
    private final static String FILE2 = "file2";
    private final static float SIZE_X=45;
    private final static float SIZE_Y=30;
    private final static float VEC_X=1;
    private final static float VEC_Y=1;
    private final static int DAMAGE=1;


    public FirePowerEnemy(float x, float y, AEnemy owner, boolean lookingRight){
        super(x-45, y-3, VEC_X, VEC_Y, SIZE_X, SIZE_Y, DAMAGE, FILE1,FILE2, "imagePath", owner, 15,  lookingRight);
        this.setFill(Color.RED);
        if(lookingRight){
            position.setpX(position.getpX()+65);
            setX(position.getpX());
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
                if(entities.get(i) instanceof AGarfield){
                    this.attack((AGarfield)(entities.get(i)));
                }
                else if(entities.get(i) instanceof Bloc || entities.get(i) instanceof RockPowerGarfield){
                    death();
                    return;
                } 
            }
        }
    }
}
