package game.garfield.character.Garfield;

import java.util.List;

import game.garfield.AEntity;
import game.garfield.App;
import game.garfield.power.APower;
import game.garfield.power.powerGarfield.FirePowerGarfield;
import game.garfield.util.Position;
import javafx.scene.paint.Color;


/**
 * {@code GarfieldFire} is a subclass of {@link AGarfield} that represents a version
 * of the Garfield character with a special fire-based attack. The character charges
 * and fires a {@link FirePowerGarfield} projectile when the attack is triggered.
 */
public class GarfieldFire extends AGarfield {
     /** The delay between attacks (in frames). */
    private static final int DELAY = 15;

    /** The file path to the character's sprite. */
    private static final String FILE_PATH = "filepath";

    /** The velocity of the character along the x-axis. */
    private static final float VX = 2;

    /** The velocity of the character along the y-axis. */
    private static final float VY = 2;

    /** Indicates whether the character is currently attacking. */
    private boolean attacking = false;

    /** The projectile used for the fire attack. */
    private FirePowerGarfield projectile;

    /** The charge time required for the fire attack. */
    private static final int CHARGE = 20;

    /** The remaining charge time for the attack. */
    private int load = CHARGE;

    /**
     * Constructs a new {@code GarfieldFire} object.
     *
     * @param p The position of the character.
     * @param hp The health points (HP) of the character.
     * @param entities The list of entities in the game.
     * @param instance The application instance associated with the character.
     */
    public GarfieldFire(Position p, int hp,List<AEntity> entities, App instance) {
        super(p.getpX(), p.getpY(),VX,VY,FILE_PATH,DELAY,entities,  instance);
        this.setFill(Color.RED);
        this.setHp(hp);
    }

    /**
     * Returns whether the character is currently attacking.
     *
     * @return {@code true} if the character is attacking, otherwise {@code false}.
     */
    public boolean getAttacking(){
        return attacking;
    }

    /**
     * Performs the attack action for the {@code GarfieldFire} character.
     * If the attack delay has passed, the character prepares to attack.
     *
     * @return The fire projectile if the attack can be performed, otherwise {@code null}.
     */
    @Override
    public APower attack(){
        if(delayAttack<1){
            attacking=true;
            projectile = new FirePowerGarfield(position.getpX(), position.getpY(), speedVector.getVectorIndex(0), speedVector.getVectorIndex(1), this,lookingRight);
            return projectile;
        }
        return null;
    }


     /**
     * Performs the fire attack animation and adds the fire projectile to the game entities.
     *
     * @return The fire projectile.
     */
    private APower doAttack() {
        //Animation
        projectile = new FirePowerGarfield(position.getpX(), position.getpY(), speedVector.getVectorIndex(0), speedVector.getVectorIndex(1), this,lookingRight);
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

     /**
     * Updates the state of the {@code GarfieldFire} character. This method is called every
     * frame and manages the attack charge and delay between attacks.
     *
     * @param hero The {@code AGarfield} character to update.
     */
    @Override
    public void update(AGarfield hero) {
        if( attacking && load<1){
            doAttack();
            attacking=false;
            load=CHARGE;
            delayAttack=DELAY;
        } else if(attacking && !(load<1)){
            load-=1;
        } else {
            delayAttack-=1;
        }
    }

}
