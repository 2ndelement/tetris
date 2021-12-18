package org.second.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

public class test extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        String music = "file:///C:/Users/27813/Videos/BiliBiliDownload/s_29350/140491572/000.mp4";
        Media media = new Media(music);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        Pane pane = new Pane();
        pane.getChildren().add(mediaView);
        mediaView.fitWidthProperty().bind(pane.widthProperty());
        mediaView.fitHeightProperty().bind(pane.heightProperty());
        Scene scene = new Scene(pane, 640, 360);
        stage.setTitle("MediaDemo");
        stage.setScene(scene);
        stage.show();
        mediaPlayer.play();
    }

    public static void main(String[] args) {
        launch();
    }
}