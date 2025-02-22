package game.garfield;
 
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;

import game.garfield.character.Garfield.AGarfield;
import game.garfield.util.IMove;
import game.garfield.util.Position;
import game.garfield.util.Vector;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * Abstract class representing a game entity in the Garfield game.
 * Provides basic functionalities such as movement, collision handling, gravity effects, 
 * and graphical representation for the entity.
 */
public abstract class AEntity extends Rectangle implements IMove {

    /**
     * List of all entities present in the game.
     */
    protected List<AEntity> entities = new ArrayList<>();

    /**
     * The position of the entity in the game world.
     */
    protected Position position;

    /**
     * The vector representing the entity's speed and direction.
     */
    protected Vector speedVector;

    /**
     * The size of the entity (width and height).
     */
    protected List<Float> size = new ArrayList<>(2);

    /**
     * Determines if gravity affects the entity.
     */
    protected boolean gravity;

    /**
     * Indicates whether the entity has collision enabled.
     */
    protected boolean collision;

    /**
     * The graphical appearance (image) of the entity.
     */
    protected Image appearance;

    /**
     * Indicates if the entity is currently facing right.
     */
    protected boolean lookingRight = false;

    /**
     * A margin used for handling entity boundaries.
     */
    protected static final double ENTITY_MARGIN = 50;

    /**
     * The root node in the JavaFX scene graph.
     */
    protected Group root = null;

    /**
     * Constructs a new AEntity object.
     * 
     * @param x          The initial X position of the entity.
     * @param y          The initial Y position of the entity.
     * @param vectorX    The horizontal speed of the entity.
     * @param vectorY    The vertical speed of the entity.
     * @param sizeX      The width of the entity.
     * @param sizeY      The height of the entity.
     * @param gravity    Whether gravity affects the entity.
     * @param collision  Whether the entity is collidable.
     * @param imagePath  The file path to the entity's appearance image.
     * @param entities   The list of all game entities.
     */
    public AEntity(float x, float y, float vectorX, float vectorY, float sizeX, float sizeY, boolean gravity, boolean collision, String imagePath,List<AEntity> entities) {
        super(sizeX,sizeY);
        this.position = new Position(x, y);
        this.setX(x);
        this.setY(y);
 
        List<Float> vect = new ArrayList<>(2);
        vect.add(0, vectorX);
        vect.add(1, vectorY);
        this.speedVector = new Vector(vect);
 
        this.size.add(0, sizeX);
        this.size.add(1, sizeY);
        this.setHeight(sizeY);
        this.setWidth(sizeX);
 
        this.gravity = gravity;
        this.collision = collision;
 
 
        this.setStrokeType(StrokeType.OUTSIDE);
        this.setStroke(Color.TRANSPARENT);
        this.setFill(Color.BLACK);
 
        this.entities=entities;
    }

   /**
     * Returns the entity is facing right.
     * 
     * @return True if the entity is facing right, false otherwise.
     */
    public boolean getLookingRight(){
        return lookingRight;
    }

     /**
     * Retrieves the list of all game entities.
     * 
     * @return The list of entities.
     */
    public List<AEntity> getEntities(){
        return entities;
    }

    /**
     * Retrieves the current position of the entity.
     * 
     * @return The entity's position.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Retrieves the size of the entity (WIDHT and HEIGHT).
     * 
     * @return The size of the entity as a list.
     */
    public List<Float> getSize() {
        return size;
    }
 
    public boolean isLookingRight() {
        return lookingRight;
    }

    /**
     * Removes the entity.
     */
    public void death(){
        entities.remove(this);
        root.getChildren().remove(this);
    }

    /**
     * Sets the direction the entity is facing.
     * 
     * @param lookingRight True if the entity is facing right, false otherwise.
     */
    public void setLookingRight(boolean lookingRight) {
        this.lookingRight = lookingRight;
    }

    /**
     * Retrieves the speed vector of the entity, with direction adjusted based on facing.
     * 
     * @return The speed vector.
     */
    public Vector getSpeedVector() {
        return (isLookingRight()) ? this.speedVector : new Vector(this.speedVector.getVectorIndex(0) * (-1), this.speedVector.getVectorIndex(1));
    }

    /**
     * Checks if the entity is affected by gravity.
     * 
     * @return True if gravity affects the entity.
     */
    public boolean isGravity() {
        return gravity;
    }

    /**
     * Checks if the entity is collidable.
     * 
     * @return True if the entity has collision enabled, false otherwise.
     */
    public boolean isCollision() {
        return collision;
    }

    /**
     * Retrieves the graphical appearance of the entity.
     * 
     * @return The entity's image.
     */
    public Image getAppearance(){
        return appearance;
    }

    /**
     * Sets the root for the entity in the JavaFX.
     * 
     * @param root The root group.
     */
    public void setRoot(Group root){
        this.root=root; 
    }

   /**
     * Gets the root for the entity in the JavaFX.
     * 
     * @param root The root group.
     */
    public Group getRoot(){
        return root;
    }

    /**
     * Updates the list of game entities.
     * 
     * @param entities The updated list of entities.
     */
    public void setEntities(List<AEntity> entities){
        this.entities=entities;
    }

     /**
     * Updates the position of the entity.
     * 
     * @param x The new X position.
     * @param y The new Y position.
     */
    public void setPosition(float x, float y) {
        this.position.setpX(x);
        this.position.setpY(y);
        this.setX(x);
        this.setY(y);
    }

 
    /**
     * Moves the entity based on the given movement type.
     * 
     * @param maxDistance The maximum distance to move.
     * @param type        The type of movement ("jump", "Left", "right", "Fall").
     * @throws Exception If the movement type is invalid.
     */
    @Override
    public void move(float maxDistance, String type) throws Exception {
        switch (type) {
            case "jump":
                this.jump(maxDistance);
                break;
            case "left":
                this.left(maxDistance);
                break;
            case "right":
                this.right(maxDistance);
                break;
            case "fall":
                this.jump(maxDistance);
                break;
            default:
                throw new Exception("No such " + type + " moving method");
        }
    }

    /**
     * Move the entity to the left.
     * 
     * @param maxDistance The maximum distance to move.
     */
    @Override
    public void left(float maxDistance) {
        this.lookingRight = false;
        this.moveRightLeft(maxDistance);
    }

     /**
     * Move the entity to the right.
     * 
     * @param maxDistance The maximum distance to move.
     */
    @Override
    public void right(float maxDistance) {
        this.lookingRight = true;
        this.moveRightLeft(maxDistance);
 
    }
 
    private void moveRightLeft(float maxDistance) {
        float whatToAdd = (maxDistance > abs(this.speedVector.getVectorIndex(0))) ? this.speedVector.getVectorIndex(0) : maxDistance;
        this.position.setpX(this.position.getpX() + whatToAdd);
        this.setTranslateX((double)this.position.getpX());
    }

    /**
     * Make the entity jump.
     * 
     * @param maxDistance The maximum distance to jump.
     */
    @Override
    public void jump(float maxDistance) {
        float whatToAdd = (maxDistance > this.speedVector.getVectorIndex(1)) ? this.speedVector.getVectorIndex(1) : maxDistance;
        this.position.setpY(this.position.getpY() + whatToAdd);
        this.setTranslateY(this.position.getpY());
    }

   /**
     * Update the entity's state based on the specified hero.
     * 
     * @param hero The hero entity affecting this entity.
     */
    @Override
    public void fall(float maxDistance) {
        float whatToAdd = (maxDistance > this.speedVector.getVectorIndex(1)) ? this.speedVector.getVectorIndex(1) : maxDistance;
        this.position.setpY(this.position.getpY() - whatToAdd);
        this.setTranslateY(this.position.getpY());
    }

    public abstract void update(AGarfield hero);
}
