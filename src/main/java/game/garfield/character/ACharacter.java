package game.garfield.character;

import java.util.List;
import game.garfield.AEntity;
import game.garfield.power.APower;

/**
 * {@code ACharacter} is an abstract class that represents a character in the game.
 * It extends {@link AEntity} and provides basic functionality such as handling health points (hp),
 * attack delays, and the ability to perform attacks through the {@link #attack()} method, which
 * is meant to be implemented by subclasses.
 */
public abstract class ACharacter extends AEntity{
    protected int hp;
    protected int delayAttack;

    /**
     * Constructs a new {@code ACharacter} object with the specified position, velocity, size,
     * health points, image, attack delay, and associated entities.
     *
     * @param x The x position of the character's position.
     * @param y The y position of the character's position.
     * @param vectorX The x-component of the character's velocity.
     * @param vectorY The y-component of the character's velocity.
     * @param sizeX The width of the character's bounding box.
     * @param sizeY The height of the character's bounding box.
     * @param hp The initial health points of the character.
     * @param imagePath The file path to the character's image.
     * @param delay The delay between attacks (in frames).
     * @param entities A list of entities in the game that are interacting with this character.
     */
    public ACharacter(float x, float y, float vectorX, float vectorY, float sizeX, float sizeY, int hp,String imagePath, int delay,List<AEntity> entities/*, List<String> fileJump, List<String> fileLeft, List<String> fileRight*/) {
        super(x, y, vectorX, vectorY, sizeX, sizeY, true, true,imagePath,entities);
        this.hp = hp;
        this.delayAttack=delay;
    }

    /**
     * Gets the health points of this character.
     *
     * @return The current health points of the character.
     */
    public int getHp() {
        return hp;
    }    
    
    /**
     * Sets the health points of this character.
     *
     * @param hp The new health points value to set for the character.
     */
    public void setHp(int hp) {
        this.hp = hp;
    }
    
    /**
     * An abstract method that represents an attack performed by this character.
     * This method must be implemented by subclasses to define how the character performs attacks.
     *
     * @return An {@link APower} object representing the attack performed by the character.
     */
    public abstract APower attack();
}
