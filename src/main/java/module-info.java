module org.second.tetris {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.second.tetris to javafx.fxml;
    exports org.second.tetris.controller;
    opens org.second.tetris.controller to javafx.fxml;
    exports org.second.tetris.app;
    opens org.second.tetris.app to javafx.fxml;
}