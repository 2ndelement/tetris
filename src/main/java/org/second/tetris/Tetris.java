package org.second.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.second.tetris.entity.GameShape;

import java.util.Arrays;

public class Tetris extends Application {
    public static int SIZE = 25;
    public static int XMAX = 10;
    public static int YMAX = 20;
    private static Pane game = new Pane();
    private static Scene scene = new Scene(game, XMAX * SIZE + 150, YMAX * SIZE);
    private static GameShape currentShape;
    private int[][] MESH = new int[XMAX][YMAX];
    public static int score = 0;

    @Override
    public void start(Stage stage) throws Exception {
        for (int[] line : MESH) {
            Arrays.fill(line, 0);
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
