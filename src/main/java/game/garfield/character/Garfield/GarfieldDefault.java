package game.garfield.character.Garfield;


import java.util.List;

import game.garfield.AEntity;
import game.garfield.App;
import game.garfield.power.APower;
import game.garfield.power.powerGarfield.DefaultPowerGarfield;
import game.garfield.util.Position;

/**
 * {@code GarfieldDefault} is a subclass of {@link AGarfield} that represents the default
 * behavior and attributes of a Garfield character in the game. It includes attack mechanics
 * and updates the character's state.
 */
public class GarfieldDefault extends AGarfield {
    /** The delay between attacks (in frames). */
    private static final int DELAY = 15;

    /** The file path to the character's sprite. */
    private static final String FILE_PATH = "filepath";

    /** The velocity of the character along the x-axis. */
    private static final float VX = 2;

    /** The velocity of the character along the y-axis. */
    private static final float VY = 2;

    /**
     * Constructs a new {@code GarfieldDefault} object.
     *
     * @param position The position of the character.
     * @param hp Health Pts of the character.
     * @param entities The list of entities in the game.
     * @param instance The application instance associated with the character.
     */
    public GarfieldDefault(Position position, int hp,List<AEntity> entities, App instance) {
        super(position.getpX(), position.getpY(),VX,VY, FILE_PATH,DELAY,entities,  instance);
        this.setHp(hp);
        //todo decide what to change for speedvector and others
    }

     /**
     * Performs the attack action for the {@code GarfieldDefault} character.
     * Creates a new {@link DefaultPowerGarfield} projectile and adds it to the projectiles list
     * and the entities in the game. The direction of the projectile is determined based on
     * the character's facing direction.
     *
     * @return The created projectile if the attack can be performed, otherwise {@code null}.
     */
    @Override
    public APower attack() {
        if(delayAttack<1){
            delayAttack=DELAY;
            DefaultPowerGarfield projectile = new DefaultPowerGarfield(position.getpX(), position.getpY(), speedVector.getVectorIndex(0), speedVector.getVectorIndex(1), this,lookingRight);
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

    /**
     * Updates the state of the {@code GarfieldDefault} character. This method is called every
     * frame and decreases the delay between attacks.
     *
     * @param hero The {@code AGarfield} character to update.
     */
    @Override
    public void update(AGarfield hero) {
        delayAttack-=1;
    }
}
