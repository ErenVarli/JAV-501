package game.garfield.util;

/**
 * Represents a contract for movement-related actions of an entity.
 * Provides methods for handling different types of movements like jumping, falling, and moving left or right.
 */
public interface IMove {

    /**
     * Moves the entity based on the specified distance and type of movement.
     * 
     * @param maxDistance The maximum distance the entity can move.
     * @param type        The type of movement (e.g., "jump", "left", "right", "fall").
     * @throws Exception If the specified type of movement is invalid.
     */
    public void move(float maxDistance, String type)throws Exception;

    /**
     * Moves the entity to the left by a specified distance.
     * 
     * @param maxDistance The maximum distance the entity can move to the left.
     */
    public void left(float maxDistance);

    /**
     * Moves the entity to the right by a specified distance.
     * 
     * @param maxDistance The maximum distance the entity can move to the right.
     */
    public void right(float maxDistance);

    /**
     * Makes the entity jump by a specified distance.
     * 
     * @param maxDistance The maximum distance the entity can the jump.
     */
    public void jump(float maxDistance);

    /**
     * Makes the entity fall by a specified distance.
     * 
     * @param maxDistance The maximum distance the entity can fthe fall.
     */
    public void fall(float maxDistance);
}
