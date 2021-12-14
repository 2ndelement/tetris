module org.second.tetris {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.second.tetris to javafx.fxml;
    exports org.second.tetris;
}