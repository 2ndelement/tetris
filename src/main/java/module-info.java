module org.second.tetris {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.junit.jupiter.api;

    exports org.second.tetris;
    opens org.second.tetris to javafx.fxml;
    exports org.second.tetris.controller;
    opens org.second.tetris.controller to javafx.fxml;
}