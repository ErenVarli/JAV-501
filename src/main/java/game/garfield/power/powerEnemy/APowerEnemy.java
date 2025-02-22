package game.garfield.power.powerEnemy;

import game.garfield.character.Garfield.AGarfield;
import game.garfield.character.Garfield.GarfieldRock;
import game.garfield.character.enemy.AEnemy;
import game.garfield.object.Bloc;
import game.garfield.power.APower;
import game.garfield.power.powerGarfield.APowerGarfield;
import game.garfield.power.powerGarfield.RockPowerGarfield;

/**
 * {@code APowerEnemy} is an abstract class that represents a power used by enemy characters in the game.
 * It extends {@link APower} and provides functionality to detect intersections with game entities,
 * such as {@link AGarfield}, blocks, or Garfield powers. This class handles the interaction
 * between enemy powers and other entities, including attacking Garfield and handling collisions.
 */
public abstract class APowerEnemy extends APower {

    /**
     * Constructs a new {@code APowerEnemy} object with specified position, velocity, size, damage,
     * file paths for images, associated enemy owner, lifetime, and facing direction.
     *
     * @param x The x position of the power's position.
     * @param y The yposition of the power's position.
     * @param vecX The x-component of the power's velocity.
     * @param vecY The y-component of the power's velocity.
     * @param sizeX The width of the power's bounding box.
     * @param sizeY The height of the power's bounding box.
     * @param damage The amount of damage the power deals.
     * @param file1 The path to the first image used for the power.
     * @param file2 The path to the second image used for the power.
     * @param imagePath The path to the power's image.
     * @param owner The enemy that owns this power.
     * @param lifeTime The lifetime of the power (in frames).
     * @param lookingRight A boolean indicating whether the power is facing right.
     */
    public APowerEnemy(float x, float y, float vecX, float vecY, float sizeX, float sizeY, int damage, String file1, String file2, String imagePath, AEnemy owner, int lifeTime, boolean lookingRight) {
        super(x, y, vecX, vecY, sizeX, sizeY, damage, file1, file2, imagePath, owner, lifeTime, lookingRight);
    }

    /**
     * Detects intersections between this power and other entities in the game world.
     * If this power intersects with an {@link AGarfield}, it attacks the Garfield character,
     * unless the Garfield is in the form of a {@link GarfieldRock} that is actively attacking.
     * If this power intersects with a {@link Bloc} or a {@link RockPowerGarfield}, the power is destroyed.
     */
    @Override
    public void detectIntersection() {
        for(int i=entities.size()-1;i>=0;i--){
            if(this.getBoundsInParent().intersects(entities.get(i).getBoundsInParent())){
                if(entities.get(i) instanceof AGarfield  && !(entities.get(i) instanceof GarfieldRock && ((GarfieldRock) entities.get(i)).getAttacking())){
                    this.attack((AGarfield)(entities.get(i)));
                }
                else if(entities.get(i) instanceof Bloc || entities.get(i) instanceof RockPowerGarfield){
                    death();
                    return;
                } 
            }
        }
    }

     /**
     * Attacks the specified {@code AGarfield} with the power's damage.
     * 
     * @param garfield The {@code AGarfield} character to attack.
     * @return {@code true} if the attack was successful, {@code false} otherwise.
     */
    public boolean attack(AGarfield garfield){
        return garfield.isAttacked(damage,owner);
    }

    /**
     * Placeholder method for interactions with a {@link APowerGarfield}.
     * This method can be overridden in subclasses to define specific interactions.
     * 
     * @param powerGarfield The {@code APowerGarfield} object involved in the interaction.
     */
    public void touchesPower(APowerGarfield powerGarfield){
    }
}
