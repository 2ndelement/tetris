module org.second.tetris {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.junit.jupiter.api;

    exports org.second.tetris;
    opens org.second.tetris to javafx.fxml;
    exports org.second.tetris.controller;
    opens org.second.tetris.controller to javafx.fxml;
}