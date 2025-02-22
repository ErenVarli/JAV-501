package game.garfield.character.Garfield;

import java.util.List;

import game.garfield.AEntity;
import game.garfield.App;
import game.garfield.power.APower;
import game.garfield.power.powerGarfield.WindPowerGarfield;
import game.garfield.util.Position;
import javafx.scene.paint.Color;

public class GarfieldIce extends AGarfield {

    private static final int DELAY = 30;
    private static final String FILE_PATH = "filepath";
    private static final float VX = 2;
    private static final float VY = 2;

    public GarfieldIce(Position p, int hp,List<AEntity> entities, App instance) {
        super(p.getpX(), p.getpY(), VX, VY, FILE_PATH, DELAY,entities,  instance);
        this.setHp(hp);
        this.setFill(Color.AQUAMARINE);

    }

    @Override
    public APower attack() {
        if (delayAttack < 1) {
            delayAttack = DELAY;
            WindPowerGarfield projectile = new WindPowerGarfield(position.getpX(), position.getpY(), speedVector.getVectorIndex(0), speedVector.getVectorIndex(1), this,lookingRight);
            projectile.setLookingRight(lookingRight);
            if (lookingRight) {
                projectile.getSpeedVector().setVectorIndex(0, projectile.getSpeedVector().getVectorIndex(0) + 1);
            } else {
                projectile.getSpeedVector().setVectorIndex(0, projectile.getSpeedVector().getVectorIndex(0) - 1);
            }
            projectiles.add(projectile);
            entities.add(projectile);
            root.getChildren().add(projectile);
            projectile.setRoot(root);
            projectile.setEntities(entities);
            return projectile;
        }
        return null;
    }

    @Override
    public void update(AGarfield hero) {
        delayAttack -= 1;
    }

}
