package game.garfield.menu;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

interface Operation {
    int execute(int a, int b);
}

public abstract  class Menu {
    protected  static void button(GridPane root, String text, int pos, Operation operation){
        Button button = new Button();
        button.setText(text);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setValignment(button, VPos.TOP);
        root.add(button, 0, pos);
        button.setOnAction(e -> {
            return;
        });
    }

    protected static void blank(GridPane root,int pos){
        Label label = new Label("");
        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setValignment(label, VPos.TOP);
        root.add(label, 0, pos);
    }
}
