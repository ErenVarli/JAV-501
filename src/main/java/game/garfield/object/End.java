package game.garfield.object;

import java.util.List;

import game.garfield.AEntity;
import game.garfield.App;
import game.garfield.character.Garfield.AGarfield;
import game.garfield.level.Level;
import game.garfield.menu.WinMenu;
import javafx.scene.paint.Color;
import javafx.stage.Stage; 

public class End extends AEntity{
    private Level level;
    private Stage stage;
    private App instance;

    public End(float x, float y, float sizeX, float sizeY, List<AEntity>entities){
        super(x, y, 0, 0, sizeX, sizeY, false, true, null,entities);
        this.setFill(Color.YELLOW);
    }

    public Level getLevel(){
        return level;
    }

    public void setLevel(Level level){
        this.level=level;
    }

    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void setInstance(App instance){
        this.instance=instance;
    }

    public void nextLevel(){
        if(level==null){
            instance.setIdLevel(1);
            WinMenu.winMenue(stage, instance.getWIDHT(), instance.getHEIGHT(), instance);
        }else{
            instance.setIdLevel(instance.getIdLevel()+1);
            instance.setCurrentLevel(level);
            instance.createScene(stage);
        }
    }

    @Override
    public void update(AGarfield hero) {
    }
}
