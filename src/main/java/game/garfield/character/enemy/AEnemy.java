package game.garfield.character.enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import game.garfield.AEntity;
import game.garfield.character.ACharacter;
import game.garfield.character.Garfield.AGarfield;
import game.garfield.character.Garfield.GarfieldFire;
import game.garfield.character.Garfield.GarfieldIce;
import game.garfield.character.Garfield.GarfieldRock;
import game.garfield.power.APower;
import game.garfield.util.Position;
import game.garfield.util.Vector;

/**
 * {@code AEnemy} is a subclass of {@link ACharacter} that represents a generic enemy in the game.
 * It can have different types (e.g., Fire, Rock, Wind) and has additional behaviors such as attack,
 * slow movement, and the ability to be attacked and change its form.
 */
public class AEnemy extends ACharacter {
    /** A list that stores the file paths for attack images. */
    protected List<String> fileAttack;

    /** The type of the enemy (Fire, Rock, Wind). */
    protected enemyType enemyType;

    /** show if the enemy is currently slowed. */
    private boolean slow = false;

    /** A timer for handling the duration of the slow effect. */
    private Timer timerSlow;

    /** The number of frames the enemy is invulnerable after being attacked. */
    protected int invFrame = 0;

    /** A timer task that ends the slow effect after a certain time. */
    private final TimerTask cancelSlow=new TimerTask(){
        public void run(){
            AEnemy.this.slow=true;
        }
    };

    /**
     * representing the different types of enemies (Fire, Rock, Wind).
     */
    enum enemyType{
        FIRE,
        ROCK,
        WIND
    }

    /**
     * Constructs a new {@code AEnemy} object with specified position, movement vector, health points,
     * image path, attack delay, and list of entities.
     *
     * @param x The x position of the enemy.
     * @param y The y position of the enemy.
     * @param vectorX The x speed of the enemy's movement vector.
     * @param vectorY The y speed of the enemy's movement vector.
     * @param hp The health pts of the enemy.
     * @param imagePath The path to the enemy's image.
     * @param delayAttack The delay between attacks (in frames).
     * @param entities The list of entities in the game.
     */
    public AEnemy(float x, float y, float vectorX, float vectorY, int hp, String imagePath, int delayAttack,List<AEntity> entities) {
        super(x, y, vectorX, vectorY, 20, 30, hp, imagePath,delayAttack,entities);
        this.fileAttack=new ArrayList<>();
    }

     /**
     * Returns the type of the enemy as a string.
     *
     * @return The type of the enemy.
     */
    public String getEnemyType() {
        return enemyType.toString();
    }
    
    /**
     * Sets the slow effect on the enemy for a brief period.
     */
    public void setSlow() {
        this.slow = true;
        this.timerSlow=new Timer();
        this.timerSlow.schedule(cancelSlow, 100);
    }

    /**
     * Returns the movement speed vector of the enemy, factoring in the slow effect if applicable.
     *
     * @return The speed vector, potentially modified by the slow effect.
     */
    @Override
    public Vector getSpeedVector() {
        Vector vect=super.getSpeedVector();
        return (slow)?new Vector((float) (vect.getVectorIndex(0)*0.1),(float)(vect.getVectorIndex(2)*0.1)):vect;
    }
    
    /**
     * Handles the behavior when the enemy is attacked. If the enemy is not currently invulnerable,
     * the health is reduced and the death sequence is triggered if health reaches zero.
     * And the enemy may transform into a new Garfield-based character when killed.
     *
     * @param damage The amount of damage dealt to the enemy.
     * @param owner The {@code AGarfield} character that attacked the enemy.
     * @return {@code true} if the enemy is still alive after the attack, otherwise {@code false}.
     */
    public boolean isAttacked(int damage, AGarfield owner) {
        
        if(invFrame>0){
            return true;
        }
        invFrame=15;
        hp-=damage;
        if(hp<1){
            death();
            owner.death();
            AGarfield newOwner;
            if(this instanceof EnemyFire){
                newOwner=new GarfieldFire(new Position(owner.getPosition().getpX(),owner.getPosition().getpY()), 1, entities, owner.getInstance());
            } else if(this instanceof EnemyRock){
                newOwner=new GarfieldRock(new Position(owner.getPosition().getpX(),owner.getPosition().getpY()), 1, entities, owner.getInstance());
            } else{
                newOwner=new GarfieldIce(new Position(owner.getPosition().getpX(),owner.getPosition().getpY()), 1, entities, owner.getInstance());
            }
            owner.transferProject(newOwner);
            newOwner.setLookingRight(owner.getLookingRight());
            entities.add(newOwner);
            root.getChildren().add(newOwner);
            newOwner.setRoot(root);
            newOwner.setEntities(entities);

            return false;
        }
        return true;
    }

    /**
     * The attack method for the enemy. This method is not implemented and should be overridden
     * by subclasses of {@code AEnemy}.
     *
     * @return An {@code APower} object representing the attack, if implemented.
     * @throws UnsupportedOperationException If called on the base {@code AEnemy} class.
     */
    @Override
    public APower attack() {
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    /**
     * Updates the state of the enemy. This method is not implemented and should be overridden
     * by subclasses of {@code AEnemy}.
     *
     * @param hero The {@code AGarfield} character to update.
     * @throws UnsupportedOperationException If called on the base {@code AEnemy} class.
     */
    @Override
    public void update(AGarfield hero) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
