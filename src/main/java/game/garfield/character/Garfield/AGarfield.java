package game.garfield.character.Garfield;

import java.util.ArrayList;
import java.util.List;

import game.garfield.AEntity;
import game.garfield.App;
import game.garfield.character.ACharacter;
import game.garfield.power.APower;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * The {@code AGarfield} class represents an abstract Garfield character in the game.
 * It extends {@link ACharacter} and manages character behavior, animations, and assets.
 */
public abstract class AGarfield extends ACharacter {

    private static final int FULL_HP = 100;
    private static final int SIZE_X = 20;
    private static final int SIZE_Y = 20;
    protected App instance;
    protected List<APower> projectiles = new ArrayList<>();
    private final List<Image> initListImg = new ArrayList<>();
    private final List<Image> movingToForwardListImg = new ArrayList<>();
    private final List<Image> movingToBackListImg = new ArrayList<>();
    private final List<Image> jumpToForwardListImg = new ArrayList<>();
    private final List<Image> attackListImg = new ArrayList<>();
    private int currentImageIndex = 0;
    private final int IMAGE_MPS = 2;

    /**
     * Constructor to initialize a Garfield character.
     *
     * @param x The initial x position of the character.
     * @param y The initial y position of the character.
     * @param vX The horizontal velocity of the character.
     * @param vY The vertical velocity of the character.
     * @param filePath The file path for the default character image.
     * @param delay The delay for character animations.
     * @param entities The list of entities interacting with the character.
     * @param instance The application instance managing the game.
     */
    public AGarfield(float x, float y, float vX, float vY, String filePath, int delay, List<AEntity> entities, App instance /*List<String> fileJump, List<String> fileLeft, List<String> fileRight*/) {
        super(x, y, vX, vY, SIZE_X, SIZE_Y, FULL_HP, filePath, delay, entities /*fileJump, fileLeft, fileRight*/);
        Image img = new Image("file:garfield/src/main/java/game/garfield/asset/1.png");
        this.setFill(new ImagePattern(img));
        this.instance = instance;
    }

    /**
     * Handles the character being attacked. Reloads the game instance.
     *
     * @param damage The damage dealt to the character.
     * @param owner The entity causing the attack.
     * @return Always returns {@code false}.
     */
    public boolean isAttacked(int damage, AEntity owner) {
        instance.reload();
        return false;
    }

    /**
     * Retrieves the current application instance.
     *
     * @return The {@code App} instance.
     */
    public App getInstance() {
        return instance;
    }

    /**
     * Gets the list of projectiles owned by the character.
     *
     * @return A list of {@code APower} projectiles.
     */
    public List<APower> getProjectiles() {
        return projectiles;
    }

    /**
     * Sets the list of projectiles for the character.
     *
     * @param projectiles A list of {@code APower} projectiles.
     */
    public void setProjectiles(List<APower> projectiles) {
        this.projectiles = projectiles;
    }

    /**
     * Transfers projectiles from this character to another Garfield character.
     *
     * @param succ The successor Garfield character.
     */
    public void transferProject(AGarfield succ) {
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).setOwner(succ);
            succ.getProjectiles().add(projectiles.get(i));
        }
    }

    /**
     * Loads initial assets for idle animations.
     */
    public void addAssetsToCollectionInit() {
        Platform.runLater(() -> {
            int nbImg = 9;
            for (int i = 1; i < nbImg; i++) {
                Image img = new Image("file:garfield/src/main/java/game/garfield/asset/" + i + ".png");
                for (int j = 0; j < IMAGE_MPS; j++) {
                    initListImg.add(img);
                }
            }
        });
    }

    /**
     * Loads assets for forward movement animations.
     */
    public void addAssetsToCollectionToMoveForward() {
        int nbImg = 7;
        for (int i = 1; i < nbImg; i++) {
            Image img = new Image("file:garfield/src/main/java/game/garfield/asset/f" + i + ".png");
            for (int j = 0; j < IMAGE_MPS; j++) {
                movingToForwardListImg.add(img);
            }
        }
    }

    /**
     * Loads assets for backward movement animations.
     */
    public void addAssetsToCollectionToMoveBack() {
        int nbImg = 7;
        for (int i = 1; i < nbImg; i++) {
            Image img = new Image("file:garfield/src/main/java/game/garfield/asset/b" + i + ".png");
            for (int j = 0; j < IMAGE_MPS; j++) {
                movingToBackListImg.add(img);
            }
        }
    }

    /**
     * Loads assets for jumping forward animations.
     */
    public void addAssetsToCollectionToJumpForward() {
        int nbImg = 6;
        for (int i = 1; i < nbImg; i++) {
            Image img = new Image("file:garfield/src/main/java/game/garfield/asset/j" + i + ".png");
            for (int j = 0; j < IMAGE_MPS; j++) {
                jumpToForwardListImg.add(img);
            }
        }
    }

    /**
     * Loads assets for attack animations.
     */
    public void addAssetsToCollectionToAttack() {
        int nbImg = 5;
        for (int i = 1; i < nbImg; i++) {
            Image img = new Image("file:garfield/src/main/java/game/garfield/asset/a" + i + ".png");
            for (int j = 0; j < IMAGE_MPS + 2; j++) {
                attackListImg.add(img);
            }
        }

    }

    /**
     * Loads all animation assets for the character.
     */
    public void addImg() {
        addAssetsToCollectionInit();
        addAssetsToCollectionToMoveForward();
        addAssetsToCollectionToMoveBack();
        addAssetsToCollectionToJumpForward();
        addAssetsToCollectionToAttack();
    }

    /**
     * Updates the character's appearance using idle animation frames.
     */
    public void movingToInitAssetsDefilment() {
        this.setFill(new ImagePattern(initListImg.get(currentImageIndex)));
        currentImageIndex = (currentImageIndex + 1) % initListImg.size();
    }

    /**
     * Updates the character's appearance using forward movement animation
     * frames.
     */
    public void movingToForwardAssetsDefilment() {
        this.setFill(new ImagePattern(movingToForwardListImg.get(currentImageIndex)));
        currentImageIndex = (currentImageIndex + 1) % movingToForwardListImg.size();
    }

    /**
     * Updates the character's appearance using backward movement animation
     * frames.
     */
    public void movingToBackAssetsDefilment() {
        this.setFill(new ImagePattern(movingToBackListImg.get(currentImageIndex)));
        currentImageIndex = (currentImageIndex + 1) % movingToBackListImg.size();
    }

    /**
     * Updates the character's appearance using attack animation frames.
     */
    public void attackToBackAssetsDefilment() {
        this.setFill(new ImagePattern(attackListImg.get(currentImageIndex)));
        currentImageIndex = (currentImageIndex + 1) % attackListImg.size();
    }

    /**
     * Updates the character's appearance using jump forward animation frames.
     */
    public void jumpingToForwardAssetsDefilment() {
        this.setFill(new ImagePattern(jumpToForwardListImg.get(currentImageIndex)));
        currentImageIndex = (currentImageIndex + 1) % jumpToForwardListImg.size();
    }

    /**
     * Initializes the character's size, position, and loads assets.
     */
    public void heroCreate() {
        this.addImg();
        this.setHeight(75);
        this.setWidth(65);
        this.setPosition(0, 500);
    }
}
