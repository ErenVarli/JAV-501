package game.garfield.move;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game.garfield.AEntity;
import game.garfield.App;
import game.garfield.character.Garfield.AGarfield;
import game.garfield.character.Garfield.GarfieldFire;
import game.garfield.character.Garfield.GarfieldRock;
import game.garfield.character.enemy.AEnemy;
import game.garfield.object.Bloc;
import game.garfield.object.End;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * Handles the movement and physics of the main character and other game
 * entities. Manages player input, gravity, jumping, attacks, and entity
 * interactions.
 */
public final class ShapeMove {

    /**
     * List of game entities that are involved in movement and interaction.
     */
    private final List<AEntity> entities;

    /**
     * The previous x-coordinate of the hero, used to revert movement if needed.
     */
    private double oldX;

    /**
     * Flag to track if the hero is jumping.
     */
    private boolean jump = false;

    /**
     * Set of keys currently pressed by the user for movement control.
     */
    private final Set<KeyCode> keyBoard = new HashSet<>();

    /**
     * The animation timeline used to update movements at a constant rate.
     */
    private final Timeline animation;

    /**
     * The hero character that the player controls.
     */
    private AGarfield hero;

    /**
     * The width of the game window used to check for boundary limits.
     */
    private final double WIDTH;

    private static final double VELOCITY = 5;
    private static final double GRAVITY = 4;
    private static final double JUMP_FORCE = 12;
    private static final int JUMP_DUR = 12;
    private static boolean jumping = false;
    private static int jumpLength = 0;
    private App instance;

    /**
     * Constructor that initializes the movement handler with the game window
     * size and scene.
     *
     * @param WIDTH The width of the game window.
     * @param scene The JavaFX scene to listen for keyboard input.
     * @param entities List of all entities in the game that will be updated.
     */
    public ShapeMove(double WIDTH, Scene scene, List<AEntity> entities, App instance, AGarfield hero) {
        this.hero = hero;
        this.WIDTH = WIDTH;
        this.entities = entities;
        this.animation = new Timeline(new KeyFrame(Duration.millis(33), e -> update()));
        this.animation.setCycleCount(Timeline.INDEFINITE);

        hero.heroCreate();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);
        this.instance=instance;
    }

    /**
     * Starts the animation loop for movement updates.
     */
    public void startAnimation() {
        animation.play();
    }

    /**
     * Stops the animation loop.
     */
    public void stopAnimation() {
        animation.stop();
    }

    /**
     * Adds a key to the set when pressed, to track user input.
     *
     * @param event The key event when a key is pressed.
     */
    public void onKeyPressed(KeyEvent event) {
        keyBoard.add(event.getCode());
    }

    /**
     * Removes a key from the set when released, to stop tracking the user
     * input.
     *
     * @param event The key event when a key is released.
     */
    public void onKeyReleased(KeyEvent event) {
        keyBoard.remove(event.getCode());
    }

    /**
     * Checks if the hero is within the x of the screen. If outside, resets
     * position.
     */
    public void detectXWindowLimit() {
        if (hero.getX() < 0 || hero.getX() > WIDTH) {
            hero.setX(this.oldX);
        }
    }

    /**
     * Checks if the hero is above the ground level or within the screen's
     * y-boundary.
     */
    public void detectYWindowLimit() {
        if (hero.getY() < 0) {
            hero.setY(0);
        }
    }

    /**
     * Moves the hero to the right if the corresponding key is pressed and no
     * attacks are happening.
     * animate hero assets
     */
    public void moveToRight() {
        if (keyBoard.contains(instance.getMoveRightKey()) && !(hero instanceof GarfieldFire && ((GarfieldFire) hero).getAttacking()) && !((hero instanceof GarfieldRock) && ((GarfieldRock) hero).getAttacking())) {
            hero.setX(hero.getX() + VELOCITY);
            hero.setLookingRight(true);
            hero.movingToForwardAssetsDefilment();
          
        }
    }

    /**
     * Moves the hero to the left if the corresponding key is pressed and no
     * attacks are happening.
     * animate hero assets
     */
    public void moveToLeft() {
        if (keyBoard.contains(instance.getMoveLeftKey()) && !((hero instanceof GarfieldFire) && ((GarfieldFire) hero).getAttacking()) && !((hero instanceof GarfieldRock) && ((GarfieldRock) hero).getAttacking())) {
            hero.movingToBackAssetsDefilment();
            hero.setX(hero.getX() - VELOCITY);
            hero.setLookingRight(false);
        }
    }

    /**
     * Applies gravity to the hero, causing them to fall unless they are
     * grounded.
     */
    public void applyGravity() {
        boolean isTouchingGround = false;

        for (AEntity entity : entities) {
            if (entity instanceof Bloc || entity instanceof AEnemy) {
                if (hero.getBoundsInParent().intersects(entity.getBoundsInParent())) {
                    if (hero.getY() + hero.getHeight() <= entity.getY() + GRAVITY) {
                        hero.setY(entity.getY() - hero.getHeight());
                        isTouchingGround = true;
                        jump = false;
                        break;
                    }
                }
            }
        }

        if (!isTouchingGround) {
            hero.setY(hero.getY() + GRAVITY);
            jump = true;
        }
    }

    /**
     * Makes the hero jump when the space key is pressed, within the jump force
     * and duration limits.
     */
    public void jump() {
        if (keyBoard.contains(instance.getJumpKey())  && !jump && !(hero instanceof GarfieldFire && ((GarfieldFire) hero).getAttacking()) && !((hero instanceof GarfieldRock) && ((GarfieldRock) hero).getAttacking())) {
            jumpLength=JUMP_DUR;
            jump = true;
            jumping = true;
        }
        if(jumping){
            if (jumpLength>0 && jump && keyBoard.contains(instance.getJumpKey())){
                hero.setY(hero.getY() - JUMP_FORCE);
                jumpLength -= 1;
            } else {
                jumping = false;
            }
        }
    }

    /**
     * Executes an attack if the enter key is pressed.
     * animate hero assets
     */
    public void attack() {
        if (keyBoard.contains(instance.getAttackKey())) {
            hero.attack();
            hero.attackToBackAssetsDefilment();
        }
    }


    public void defaultMove(){
        if (keyBoard.isEmpty()) {
            hero.movingToInitAssetsDefilment();
        }
    }

    /**
     * Checks for intersections or collisions between the hero and other
     * entities. Handles behaviors when interacting with obstacles, enemies, and
     * level transitions.
     */
    public void detectIntersection() {
        for (AEntity entity : entities) {
            if (entity instanceof Bloc || entity instanceof AEnemy) {
                if (hero.getBoundsInParent().intersects(entity.getBoundsInParent())) {

                    if (hero.getY() + hero.getHeight() <= entity.getY()) {
                        hero.setY(entity.getY() - hero.getHeight());
                        jump = false;
                    } else if (hero.getY() >= entity.getY() + entity.getHeight()) {
                        hero.setY(entity.getY() + entity.getHeight());
                    }

                    if (hero.getX() + hero.getWidth() > entity.getX() && hero.getX() < entity.getX() + entity.getWidth()) {
                        if (hero.getY() + hero.getHeight() > entity.getY() && hero.getY() < entity.getY() + entity.getHeight()) {
                            hero.setX(oldX);
                        }
                    }
                }
            } else if (entity instanceof End && hero.getBoundsInParent().intersects(entity.getBoundsInParent())) {
                animation.stop();
                ((End) entity).nextLevel();
            }

        }
    }

    /**
     * Controls the movement actions based on keyboard input.
     */
    public void keyBoardMove() {
        moveToRight();
        moveToLeft();
        jump();
        attack();
        defaultMove();
    }

    /**
     * Updates the position and status of all entities, applying movements,
     * gravity, and collision checks.
     */
    public void update() {
        for (AEntity entity : entities) {
            if (entity instanceof AGarfield) {
                hero = (AGarfield) entity;
            }
        }

        this.oldX = hero.getX();
        hero.getPosition().setpX((float) hero.getX());
        hero.getPosition().setpY((float) hero.getY());
        keyBoardMove();
        applyGravity();
        detectIntersection();
        detectXWindowLimit();
        detectYWindowLimit();
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (i >= entities.size()) {
                i = entities.size() - 1;
            }
            entities.get(i).update(hero);
        }
    }
}
