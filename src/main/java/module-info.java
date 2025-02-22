module game.garfield {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires javafx.graphics;

    opens game.garfield to javafx.fxml;
    exports game.garfield;
    exports game.garfield.level;
}
