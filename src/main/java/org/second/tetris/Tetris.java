package org.second.tetris;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.second.tetris.entity.AchoredRectanglesManager;
import org.second.tetris.entity.GameShape;
import org.second.tetris.entity.Shape.Cell;
import org.second.tetris.entity.Shape.ShapeFactory;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris extends Application {
    public static int LEFT = 100;
    public static int RIGHT = 100;
    public static int SIZE = 25;
    public static int XMAX = 10;
    public static int YMAX = 20;
    private static Pane game = new Pane();
    private static Scene scene = new Scene(game, XMAX * SIZE + LEFT + RIGHT, YMAX * SIZE);
    private static GameShape currentShape;
    private static GameShape previewShape;
    private static int[][] MESH = new int[YMAX][XMAX];
    public static int score = 0;
    public static boolean isOver = false;
    private static AchoredRectanglesManager manager = new AchoredRectanglesManager(game, MESH);

    @Override
    public void start(Stage stage) throws Exception {
        drawBackgroud();
        stage.setScene(scene);
        stage.setTitle("TETRIS");
        stage.show();
        for (int[] line : MESH) {
            Arrays.fill(line, 0);
        }
        addNewShape();
        moveOnKeyPress();
        autoFall();
    }

    private void autoFall() {
        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(
                        () -> {
                            if (isOver) {
                                System.exit(1);
                            }
                            MoveDown();
                        }
                );
            }
        };
        fall.schedule(task, 0, 1000);
    }

    private void MoveRight() {
        currentShape.moveRight(MESH);
        flushPrew();
    }

    private void MoveDown() {
        if (!currentShape.moveDown(MESH)) {
            removeShape(previewShape);
            score += manager.anchorShape(currentShape);
            addNewShape();
        }
    }

    private void moveOnKeyPress() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT:
                case D:
                    MoveRight();
                    break;
                case DOWN:
                case S:
                    MoveDown();
                    break;
                case LEFT:
                case A:
                    MoveLeft();
                    break;
                case UP:
                case W:
                    RSpin();
                    break;
                case Q:
                case Z:
                    LSpin();
                    break;
                case SPACE:
                    while (currentShape.moveDown(MESH)) ;
                    MoveDown();
                    break;
                case F:
            }

        });
    }

    private void LSpin() {
        currentShape.lSpin(MESH);
        flushPrew();
    }

    private void RSpin() {
        currentShape.rSpin(MESH);
        flushPrew();
    }

    private void MoveLeft() {
        currentShape.moveLeft(MESH);
        flushPrew();
    }

    private void flushPrew() {
        removeShape(previewShape);
        previewShape = currentShape.createPrew(MESH);
        drawShape(previewShape);
    }


    private void drawBackgroud() {
        game.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        for (int i = 0; i < XMAX; i++) {
            for (int j = 0; j < YMAX; j++) {
                Rectangle rect = new Rectangle(LEFT + i * SIZE, j * SIZE, SIZE - 1, SIZE - 1);
                rect.setFill(Color.BLACK);
                game.getChildren().add(rect);
            }
        }
    }

    private void addNewShape() {
        currentShape = new GameShape(ShapeFactory.nextShape());
        checkOver(currentShape);
        previewShape = currentShape.createPrew(MESH);
        drawShape(previewShape);
        drawShape(currentShape);
    }

    private void checkOver(GameShape currentShape) {
        for (Cell cell : currentShape.getShape()) {
            if (MESH[cell.getY()][cell.getX()] == 1) {
                isOver = true;
                return;
            }
        }
    }

    private void drawShape(GameShape shape) {
        for (Rectangle rect : shape) {
            game.getChildren().add(rect);
        }
    }

    private void removeShape(GameShape shape) {
        for (Rectangle rect : shape) {
            game.getChildren().remove(rect);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
