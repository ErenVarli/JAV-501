package game.garfield.object;

import java.util.List;

import game.garfield.AEntity;
import game.garfield.character.Garfield.AGarfield;

public class Kibble extends AEntity{
    private int heal;

    public Kibble(float x, float y,int heal,List<AEntity>entities){
        super(x, y, 0, 0, 1, 1, false, true, "imagePath",entities);
        this.heal=heal;
    }

    public int getHeal(){
        return heal;
    }

    @Override
    public void update(AGarfield hero) {
    }
}
