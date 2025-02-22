package game.garfield.object;

import java.util.List;

import game.garfield.AEntity;
import game.garfield.character.Garfield.AGarfield;

public class Floor extends AEntity{
    private float speed=1;
    private int idCurrentGoal = 0;

    public Floor(float x, float y, float sizeX,String imagePath, List<AEntity>entities){
        super(x, y, 0, 0, sizeX, 10, false, true, imagePath,entities);
    }

    public float getSpeed(){
        return speed;
    }

    public void setSpeed(float speed){
        this.speed=speed;
    }

    @Override
    public void update(AGarfield hero) {
    }
}
