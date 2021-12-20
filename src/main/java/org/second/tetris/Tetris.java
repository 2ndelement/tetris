package org.second.tetris;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.second.tetris.entity.AchoredRectanglesManager;
import org.second.tetris.entity.GameScorePane;
import org.second.tetris.entity.GameShape;
import org.second.tetris.entity.Shape.Cell;
import org.second.tetris.entity.Shape.ShapeFactory;
import org.second.tetris.entity.Shape.Tetromino;
import org.second.tetris.entity.ShapePane;
import org.second.tetris.utils.TetrisColor;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris extends Application {
    public static final int LEFT = 500;
    public static final int RIGHT = 500;
    public static final int TOP = 30;
    public static final int BOTTOM = 30;
    public static int SIZE = 30;
    public static int XMAX = 10;
    public static int YMAX = 20;
    private static boolean isPause = false;
    private static final Pane game = new Pane();
    private static final Scene scene = new Scene(game, XMAX * SIZE + LEFT + RIGHT, TOP + YMAX * SIZE + BOTTOM);
    private static GameShape currentShape;
    private static GameShape previewShape;
    private static final int[][] MESH = new int[YMAX][XMAX];
    public static GameScorePane score;
    public static boolean isOver = false;
    private static final AchoredRectanglesManager manager = new AchoredRectanglesManager(game, MESH);
    private static MediaPlayer mediaPlayer;
    private static ShapePane holdPane = null;
    private static final ShapePane[] nextPanes = new ShapePane[5];
    private static final Timer timer = new Timer();
    private static TimerTask fall = null;
    private static int holdCount = 0;
    private static long speed = 1000;
    private Stage stage;

    /**
     * 背景和框图创建
     */
    private void drawBackgroud() {
        score = new GameScorePane(LEFT + XMAX, TOP + YMAX * SIZE + SIZE, SIZE, 0);
        game.getChildren().add(score);
        nextPanes[0] = new ShapePane(LEFT + XMAX * SIZE + 10, TOP, SIZE / 2, 0);
        nextPanes[1] = new ShapePane(LEFT + XMAX * SIZE + 10, TOP + 6 * SIZE / 2, SIZE / 2, 0);
        nextPanes[2] = new ShapePane(LEFT + XMAX * SIZE + 10, TOP + 12 * SIZE / 2, SIZE / 2, 0);
        nextPanes[3] = new ShapePane(LEFT + XMAX * SIZE + 10, TOP + 18 * SIZE / 2, SIZE / 2, 0);
        nextPanes[4] = new ShapePane(LEFT + XMAX * SIZE + 10, TOP + 24 * SIZE / 2, SIZE / 2, 10);
        for (ShapePane pane : nextPanes) {
            game.getChildren().add(pane);
            pane.showShape(ShapeFactory.nextShape());
        }
        BackgroundImage myBI = new BackgroundImage(new Image(String.valueOf(this.getClass().getResource("1.jpg"))), null, null, null, null);
        game.setBackground(new Background(myBI));
        holdPane = new ShapePane(LEFT - SIZE * 6 - 10, TOP, SIZE, 20);
        game.getChildren().add(holdPane);
        Rectangle gameBounds = new Rectangle(LEFT - 10, TOP - 10, XMAX * SIZE + 20, YMAX * SIZE + 20);
        gameBounds.setArcWidth(20);
        gameBounds.setArcHeight(20);
        gameBounds.setFill(TetrisColor.GRID);
        gameBounds.setOpacity(0.8);
        game.getChildren().add(gameBounds);
        for (int i = 0; i < XMAX; i++) {
            for (int j = 0; j < YMAX; j++) {
                Rectangle rect = new Rectangle(LEFT + i * SIZE, BOTTOM + j * SIZE, SIZE - 1, SIZE - 1);
                rect.setFill(TetrisColor.BACKGROUND);
                game.getChildren().add(rect);
            }
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        isOver = false;
        this.stage = stage;
        Random random = new Random();
        URL music = Tetris.class.getResource(random.nextInt(3) + 1 + ".mp3");
        Media media = new Media(String.valueOf(music));
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(mediaPlayer::play);
        drawBackgroud();
        stage.setScene(scene);
        stage.setTitle("TETRIS");
        stage.show();
        for (int[] line : MESH) {
            Arrays.fill(line, 0);
        }
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        addNewShape();
        moveOnKeyPress();
        startFall();
    }

    private void startFall() {
        timer.purge();
        if (fall == null) {
            fall = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(
                            () -> {
                                if (isOver) {
                                    try {
                                        HelloApplication.getUser().addRecord(score.getScore());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    exit();

                                } else {
                                    MoveDown();
                                }
                            }
                    );
                }
            };
        }
        timer.schedule(fall, 0, score.speed());
    }

    private void exit() {
        stopFall();
        stage.close();
        try {
            new HelloApplication().start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopFall() {

        fall.cancel();
        fall = null;
    }

    private void MoveRight() {
        removeShape(previewShape);
        if (isPause) {
            startFall();
            isPause = false;
        }
        currentShape.moveRight();
        flushPrew();
    }

    private void MoveDown() {
        if (isPause) {
            startFall();
            isPause = false;
        }
        if (!currentShape.moveDown()) {
            score.add(manager.anchorShape(currentShape));
            holdCount = 0;
            addNewShape();
            flushFall();
        }
    }

    private void flushFall() {
        if (speed != score.speed()) {
            stopFall();
            startFall();
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
                    while (currentShape.moveDown()) ;
                    MoveDown();
                    break;
                case C:
                    if (holdCount == 0) {
                        holdCurrent();
                        holdCount++;
                    }
                    break;
                case P:
                    if (isPause) {
                        startFall();
                        isPause = false;
                    } else {
                        stopFall();
                        isPause = true;
                    }
                    break;
                case ESCAPE:
                    exit();
                    break;
                case M:
                    if (mediaPlayer.isMute()) {
                        mediaPlayer.setMute(false);
                    } else {
                        mediaPlayer.setMute(true);
                    }
            }

        });
    }

    private void holdCurrent() {
        if (holdPane.hasShowed()) {
            Tetromino changeShape = holdPane.getSingleShapeInstance();
            removeShape(currentShape);
            removeShape(previewShape);
            holdPane.showShape(currentShape.getShape());
            addNewShape(new GameShape(changeShape, MESH));


        } else {
            holdPane.showShape(currentShape.getShape());
            removeShape(currentShape);
            removeShape(previewShape);
            addNewShape();
        }
    }

    private void LSpin() {
        removeShape(previewShape);
        if (isPause) {
            startFall();
            isPause = false;
        }
        currentShape.lSpin();
        flushPrew();
    }

    private void RSpin() {
        removeShape(previewShape);
        if (isPause) {
            startFall();
            isPause = false;
        }
        currentShape.rSpin();
        flushPrew();
    }

    private void MoveLeft() {
        removeShape(previewShape);
        if (isPause) {
            startFall();
            isPause = false;
        }
        currentShape.moveLeft();
        flushPrew();
    }

    private void flushPrew() {
        removeShape(previewShape);
        previewShape = currentShape.createPrew();
        drawShape(previewShape);
    }

    private void addNewShape() {
        removeShape(previewShape);
        currentShape = nextShape();
        checkOver(currentShape);
        previewShape = currentShape.createPrew();
        drawShape(previewShape);
        drawShape(currentShape);
    }

    private GameShape nextShape() {
        GameShape nextShape = new GameShape(nextPanes[0].getNewShapeInstance(), MESH);
        for (int i = 0; i < nextPanes.length - 1; i++) {
            nextPanes[i].showShape(nextPanes[i + 1].getSingleShapeInstance());
        }
        nextPanes[nextPanes.length - 1].showShape(ShapeFactory.nextShape());
        return nextShape;
    }

    private void addNewShape(GameShape shape) {
        currentShape = shape;
        checkOver(currentShape);
        previewShape = currentShape.createPrew();
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
        if (shape != null) {
            for (Rectangle rect : shape) {
                game.getChildren().remove(rect);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
