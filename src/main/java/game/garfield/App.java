package game.garfield;

import java.util.ArrayList;
import java.util.List;

import game.garfield.character.Garfield.AGarfield;
import game.garfield.level.Level;
import game.garfield.menu.MainMenu;
import game.garfield.move.ShapeMove;
import game.garfield.object.Background;
import game.garfield.object.End;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main application class for the Garfield game.
 * Handles the game lifecycle, level management, and scene rendering.
 */

public class App extends Application {

    private List<AEntity> entities = new ArrayList<>();
    private static final double WIDTH = 1200;
    private static final double HEIGHT = 800;
    private AGarfield hero;
    private Level currentLevel;
    private int idLevel=1;
    private Stage stage;
    private ShapeMove gameLoop;
    private KeyCode attackKey = KeyCode.ENTER;
    private KeyCode moveRightKey = KeyCode.RIGHT;
    private KeyCode moveLeftKey = KeyCode.LEFT;
    private KeyCode jumpKey = KeyCode.SPACE;

    public KeyCode getAttackKey() {
        return attackKey;
    }

    public void setAttackKey(KeyCode attackKey) {
        this.attackKey = attackKey;
    }

    public KeyCode getMoveRightKey() {
        return moveRightKey;
    }

    public void setMoveRightKey(KeyCode moveRightKey) {
        this.moveRightKey = moveRightKey;
    }

    public KeyCode getMoveLeftKey() {
        return moveLeftKey;
    }

    public void setMoveLeftKey(KeyCode moveLeftKey) {
        this.moveLeftKey = moveLeftKey;
    }

    public KeyCode getJumpKey() {
        return jumpKey;
    }

    public void setJumpKey(KeyCode jumpKey) {
        this.jumpKey = jumpKey;
    }

    /**
     * Gets the current level ID.
     *
     * @return the ID of the current level
     */
    
    public int getIdLevel(){
        return idLevel;
    }

     /**
     * Sets the current level ID.
     *
     * @param idLevel the ID of the level to set
     */
    
    public void setIdLevel(int idLevel){
        this.idLevel=idLevel;
    }

      /**
     * Sets the current level and initializes it.
     *
     * @param currentLevel the level to set as current
     */
    
    public void setCurrentLevel(Level currentLevel){
        currentLevel.current();
        this.currentLevel=currentLevel;
    }

    /**
     * Loads the game levels and sets the initial level to1.
     */
    
    public void load(){
        Level level1 = new Level("level1Data.json", null, null, this);
        Level level2 = new Level("level2Data.json", null, level1, this);
        Level level3 = new Level("level3Data.json", null, level2, this);
        level1.setNextLevel(level2);
        level2.setNextLevel(level3);
        setCurrentLevel(level1);
        currentLevel=level1;
    }

    /**
     * Reloads the current level and restarts the game loop.
     */
    
    public void reload(){
        currentLevel=new Level("level"+idLevel+"Data.json", currentLevel.getNextLevel(), currentLevel.getPrevLevel(), this);
        setCurrentLevel(currentLevel);
        gameLoop.stopAnimation();
        createScene(stage);
    }


     /**
     * Creates and initializes the game scene with the current level entities.
     *
     * @param stge the primary stage for the application
     */
    
    public void createScene(Stage stage) {
        entities = currentLevel.getEntities();

        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);


        for (AEntity entity : entities) {
            root.getChildren().add(entity);
            entity.setRoot(root);

            if (entity instanceof AGarfield) {
                this.hero = (AGarfield) entity;
            }
            if(entity instanceof End){
                ((End) entity).setLevel(currentLevel.getNextLevel());
                ((End) entity).setInstance(this);
                ((End) entity).setStage(stage);
            }
            
        }
        
        Background background=new Background((float)0,(float)0,(float)WIDTH,(float) HEIGHT,"test",entities);
        root.getChildren().add(background);
        background.toBack();
        createHero(root, scene);
        startWindows(stage, scene);
    }

    /**
     * Starts the application window with a specified scene.
     *
     * @param stage the primary stage for the application
     * @param scene the scene to display in the stage
     */
    public static void startWindows(Stage stage, Scene scene) {
        stage.setTitle("Garfield");
        stage.sizeToScene();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Starts the game with an initial screen displaying a start button.
     *
     * @param stage the primary stage for the application
     * @param title the title of the start button
     */
    
    public void startGame(Stage stage, String title) {
        BorderPane root = new BorderPane();
        Scene restartScene = new Scene(root, WIDTH, HEIGHT);
        Button restartBtn = new Button();
        restartBtn.setText(title);
        root.setCenter(restartBtn);

        restartBtn.setOnAction(e -> {
            createScene(stage);
            System.out.println("Game started");
        });

        stage.setTitle("Garfield");
        stage.setScene(restartScene);
        stage.show();
    }

    /**
     * Creates and initializes the hero character and its movement logic.
     *
     * @param root  the root grp containing all game entities
     * @param scene the scene where the hero will play
     */
    
    public void createHero(Group root, Scene scene) {
        ShapeMove moving = new ShapeMove(WIDTH, scene, entities, this, hero);
        gameLoop=moving;
        moving.startAnimation();
    }

    /**
     * Starts the application.
     *
     * @param stage the primary stage for the application
     */
    
    @Override
    public void start(Stage stage) {
        startGame(stage, "Start game");
        this.stage=stage;
        MainMenu.mainMenue(stage, WIDTH, HEIGHT, this);
    }

    /**
     * Main entry point for the app.
     *
     * @param args the command-line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Gets the application window width. We will use that for ShapeMove to determinate character's limit axes.
     *
     * @return the width of the window
     */
    
    public double getWIDHT() {
        return WIDTH;
    }

      /**
     * Gets the application window height. We will use that for ShapeMove to determinate character's limit axes.
     *
     * @return the height of the window
     */
    
    public double getHEIGHT() {
        return HEIGHT;
    }
}
