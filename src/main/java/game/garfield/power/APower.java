package game.garfield.power;

import java.util.ArrayList;
import java.util.List;

import game.garfield.AEntity;
import game.garfield.character.ACharacter;
import game.garfield.character.Garfield.AGarfield;


/**
 * Represents an abstract power entity in the game.
 * Powers are associated with characters and have attributes like damage, movement patterns, and lifetime.
 */
public abstract class APower extends AEntity{
   /**
     * The owner of the power (usually a character).
     */
    protected ACharacter owner;

    /**
     * The damage inflicted by this power.
     */
    protected int damage;

    /**
     * List of movements or actions associated with the power.
     */
    protected List<String> listMov;

    /**
     * The lifetime of the power, representing how long it remains active.
     */
    protected int lifeTime;

    /**
     * Constructor to initialize the power entity with position, size, behavior, and other attributes.
     * 
     * @param x           The x-coordinate of the power's position.
     * @param y           The y-coordinate of the power's position.
     * @param vecX        The x-component of the power's speed vector.
     * @param vecY        The y-component of the power's speed vector.
     * @param sizeX       The width of the power entity.
     * @param sizeY       The height of the power entity.
     * @param damage      The damage caused by the power.
     * @param file1       The first movement or action file.
     * @param file2       The second movement or action file.
     * @param imagePath   The path to the image representing the power.
     * @param owner       The character owning this power.
     * @param lifeTime    The duration for which the power remains active.
     * @param lookingRight A boolean indicating if the power faces right.
     */
    public APower(float x, float y, float vecX, float vecY, float sizeX, float sizeY, int damage, String file1,String file2, String imagePath, ACharacter owner, int lifeTime, boolean lookingRight){
        super(x, y, vecX, vecY, sizeX, sizeY, false, true, imagePath,owner.getEntities());
        this.lookingRight=lookingRight;
        this.damage=damage;
        this.listMov=this.loadMov(file1, file2);
        this.owner=owner;
        this.lifeTime=lifeTime;
    }


     /**
     * get the damage caused by this power.
     * 
     * @return The damage value.
     */
    public int getDamage(){
        return damage;
    }

    /**
     * get the owner of this power.
     * 
     * @return The entity owning this power.
     */
    public AEntity getOwner(){
        return owner;
    }

    public void setOwner(ACharacter owner){
        this.owner=owner;
    }


    /**
     * Updates the state of the power. Lose progressively lifetime and handles interactions.
     * 
     * @param hero The main character (Garfield) interacting with this power.
     */
    @Override
    public void update(AGarfield hero) {
        lifeTime -= 1;
        if(lifeTime<1){
            death();
        }
        // detectXWindowLimit();
        // detectYWindowLimit();
        detectIntersection();
    }

    /**
     * Loads the movement or action files associated with this power.
     * 
     * @param file1 The first movement file.
     * @param file2 The second movement file.
     * @return A list of movements loaded from the files.
     */
    protected List<String> loadMov(String file1,String file2){
        List<String> listMov = new ArrayList<>();
        listMov.add(file1);
        listMov.add(file2);
        return listMov;
    }

     /**
     * Abstract method to detect intersections or collisions involving the power.
     * This must be implemented by subclasses to define specific behavior.
     */
    public abstract void detectIntersection();
}
