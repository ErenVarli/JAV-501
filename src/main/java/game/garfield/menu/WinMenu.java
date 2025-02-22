package game.garfield.menu;

import game.garfield.App;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WinMenu extends Menu{

    public static void winMenue(Stage stage, double WIDTH,double HEIGHT,App appInstance) {
        GridPane root = new GridPane();
        Scene MainMenue = new Scene(root, WIDTH, HEIGHT);
        root.setAlignment(Pos.CENTER);

        Label label = new Label("You have won!!");
        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setValignment(label, VPos.TOP);
        root.add(label, 0, 0);

        blank(root,1);

        Button start = new Button();
        start.setText("Restart");
        GridPane.setHalignment(start, HPos.CENTER);
        GridPane.setValignment(start, VPos.TOP);
        root.add(start, 0, 2);
        start.setOnAction(e -> {
            appInstance.load();
            appInstance.createScene(stage);
        });

        blank(root,3);

        Button option = new Button();
        option.setText("Option");
        GridPane.setHalignment(option, HPos.CENTER);
        GridPane.setValignment(option, VPos.TOP);
        root.add(option, 0, 4);
        option.setOnAction(e -> {
            OptionMenu.optionMenue(stage, WIDTH, HEIGHT,appInstance);
        });

        blank(root,5);

        Button quit = new Button();
        quit.setText("Quit");
        GridPane.setHalignment(quit, HPos.CENTER);
        GridPane.setValignment(quit, VPos.TOP);
        root.add(quit, 0, 6);
        quit.setOnAction(e -> {
            stage.close();
        });

        stage.setTitle("Garfield");
        stage.setScene(MainMenue);
        stage.show();
    }
}
