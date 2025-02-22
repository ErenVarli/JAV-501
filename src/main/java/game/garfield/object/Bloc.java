package game.garfield.object;

import java.util.List;
import game.garfield.AEntity;
import game.garfield.character.Garfield.AGarfield;

/**
 * {@code Bloc} represents a background object in the game.
 * It extends {@link AEntity} and is used to set and display the background image
 * in the game scene.
 */
public class Bloc extends AEntity{
    /**
     * Constructs a new {@code Bloc} object with the specified position, size,
     * and image to represent the background of the game.
     *
     * @param x The x position of the background object.
     * @param y The y positon of the background object.
     * @param sizeX The width of the background.
     * @param sizeY The height of the background.
     * @param imagePath The file path to the background image.
     * @param entities A list of entities interacting with this background object.
     */
    private float speed=1;
    public Bloc(float x, float y, float sizeX, float sizeY,String imagePath,List<AEntity>entities){
        super(x, y, 0, 0, sizeX, sizeY, false, true, imagePath,entities);
    }

     /**
     * Gets the speed of the block.
     *
     * @return The speed of the block.
     */
    public float getSpeed(){
        return speed;
    }

     /**
     * Sets the speed of the block.
     *
     * @param speed The new speed of the block.
     */
    public void setSpeed(float speed){
        this.speed=speed;
    }

    /**
     * Update method for the {@code Background} class.
     * This method does not perform any updates in the case of the background
     * object as it is static in the scene.
     *
     * @param hero The main character (hero) of the game, passed for potential future use
     *             in interactions or updates, but currently unused in this method.
     */
    @Override
    public void update(AGarfield hero) {
    }
}
