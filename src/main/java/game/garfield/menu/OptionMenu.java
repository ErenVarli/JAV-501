package game.garfield.menu;

import game.garfield.App;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OptionMenu extends Menu {

    public static void optionMenue(Stage stage, double WIDTH, double HEIGHT, App appInstance) {
        GridPane root = new GridPane();
        Scene optionMenue = new Scene(root, WIDTH, HEIGHT);
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);

        Label attackLabel = new Label("Attack Key:");
        TextField attackKeyField = new TextField(appInstance.getAttackKey().toString());
        Button attackSave = new Button("Save");

        root.add(attackLabel, 0, 0);
        root.add(attackKeyField, 1, 0);
        root.add(attackSave, 2, 0);
        GridPane.setHalignment(attackSave, HPos.CENTER);

        attackSave.setOnAction(e -> {
            try {
                appInstance.setAttackKey(KeyCode.valueOf(attackKeyField.getText().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                attackKeyField.setText("Invalid Key");
            }
        });

        Label moveRightLabel = new Label("Move Right Key:");
        TextField moveRightKeyField = new TextField(appInstance.getMoveRightKey().toString());
        Button moveRightSave = new Button("Save");

        root.add(moveRightLabel, 0, 1);
        root.add(moveRightKeyField, 1, 1);
        root.add(moveRightSave, 2, 1);
        GridPane.setHalignment(moveRightSave, HPos.CENTER);

        moveRightSave.setOnAction(e -> {
            try {
                appInstance.setMoveRightKey(KeyCode.valueOf(moveRightKeyField.getText().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                moveRightKeyField.setText("Invalid Key");
            }
        });

        Label moveLeftLabel = new Label("Move Left Key:");
        TextField moveLeftKeyField = new TextField(appInstance.getMoveLeftKey().toString());
        Button moveLeftSave = new Button("Save");

        root.add(moveLeftLabel, 0, 2);
        root.add(moveLeftKeyField, 1, 2);
        root.add(moveLeftSave, 2, 2);
        GridPane.setHalignment(moveLeftSave, HPos.CENTER);

        moveLeftSave.setOnAction(e -> {
            try {
                appInstance.setMoveLeftKey(KeyCode.valueOf(moveLeftKeyField.getText().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                moveLeftKeyField.setText("Invalid Key");
            }
        });

        Label jumpLabel = new Label("Jump Key:");
        TextField jumpKeyField = new TextField(appInstance.getJumpKey().toString());
        Button jumpSave = new Button("Save");

        root.add(jumpLabel, 0, 3);
        root.add(jumpKeyField, 1, 3);
        root.add(jumpSave, 2, 3);
        GridPane.setHalignment(jumpSave, HPos.CENTER);

        jumpSave.setOnAction(e -> {
            try {
                appInstance.setJumpKey(KeyCode.valueOf(jumpKeyField.getText().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                jumpKeyField.setText("Invalid Key");
            }
        });

        Button back = new Button();
        back.setText("Back");
        GridPane.setHalignment(back, HPos.CENTER);
        GridPane.setValignment(back, VPos.TOP);
        root.add(back, 1, 5);

        back.setOnAction(e -> {
            MainMenu.mainMenue(stage, WIDTH, HEIGHT, appInstance);
        });

        stage.setTitle("Garfield - Options");
        stage.setScene(optionMenue);
        stage.show();
    }
}