package game.garfield.object;

import java.util.List;
import game.garfield.AEntity;
import game.garfield.character.Garfield.AGarfield;

/**
 * {@code Crate} represents a crate object in the game.
 * It extends {@link AEntity} and is used to display a crate in the game scene.
 * The crate can interact with other entities, but its behavior is currently static.
 */
public class Crate extends AEntity{
    /**
     * Constructs a new {@code Crate} object with the specified position, velocity, size, 
     * and skin to represent the crate in the game.
     *
     * @param x The x position of the crate.
     * @param y The y position of the crate.
     * @param vectorX The x velocity of the crate.
     * @param vectorY The y velocity of the crate.
     * @param sizeX The width of the crate.
     * @param sizeY The height of the crate.
     * @param skin The file path to the image representing the crate's appearance.
     * @param entities A list of entities interacting with the crate.
     */
    public Crate(float x, float y, float vectorX, float vectorY, float sizeX, float sizeY, String skin,List<AEntity>entities){
        super(x, y, vectorX, vectorY, sizeX, sizeY, true, true, skin,entities);
    }

    /**
     * Update method for the {@code Crate} class.
     * This method does not perform any updates for the crate as it is a static object
     * in the game scene.
     *
     * @param hero The main character (hero) of the game, passed for potential future use
     *             in interactions or updates, but currently unused in this method.
     */
    @Override
    public void update(AGarfield hero) {
    }
}
