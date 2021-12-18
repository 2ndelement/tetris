package org.second.tetris.entity;

import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.second.tetris.entity.Shape.Tetromino;
import org.second.tetris.utils.TetrisColor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class ShapePane extends Pane {
    private final Rectangle[][] rects = new Rectangle[6][5];
    private final Set<Rectangle> showedRects = new HashSet<>();
    private Tetromino shape;

    public ShapePane(int x, int y, int size, int arc) {
        super();
        setLayoutX(x);
        setLayoutY(y);
        Rectangle background = new Rectangle(size * 6, size * 6);
        background.setFill(TetrisColor.GRID);
        background.setOpacity(0.8);
        background.setArcHeight(arc);
        background.setArcWidth(arc);
        getChildren().add(background);
        for (int i = 0; i < rects.length; i++) {
            for (int j = 0; j < rects[i].length; j++) {
                Rectangle rect = new Rectangle(i * size, j * size, size - 1, size - 1);
                rect.setEffect(new Lighting());
                rect.setVisible(false);
                rects[i][j] = rect;
                getChildren().add(rect);
            }
        }
    }

    public Tetromino getSingleShapeInstance() {
        return shape;
    }

    public Tetromino getNewShapeInstance() {
        try {
            return shape.getClass().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean hasShowed() {
        return shape != null;
    }

    public void showShape(Tetromino tetromino) {
        shape = tetromino;
        clearAll();
        String[] names = shape.getClass().getName().split("\\.");
        String className = names[names.length - 1];
        switch (className) {
            case "IShape":
                showedRects.add(rects[1][3]);
                showedRects.add(rects[2][3]);
                showedRects.add(rects[3][3]);
                showedRects.add(rects[4][3]);
                for (Rectangle rect : showedRects) {
                    rect.setFill(TetrisColor.I);
                }
                break;
            case "JShape":
                showedRects.add(rects[1][3]);
                showedRects.add(rects[2][3]);
                showedRects.add(rects[3][3]);
                showedRects.add(rects[3][2]);
                for (Rectangle rect : showedRects) {
                    rect.setFill(TetrisColor.J);
                }
                break;
            case "LShape":
                showedRects.add(rects[2][2]);
                showedRects.add(rects[2][3]);
                showedRects.add(rects[3][3]);
                showedRects.add(rects[4][3]);
                for (Rectangle rect : showedRects) {
                    rect.setFill(TetrisColor.L);
                }
                break;
            case "OShape":
                showedRects.add(rects[2][2]);
                showedRects.add(rects[2][3]);
                showedRects.add(rects[3][2]);
                showedRects.add(rects[3][3]);
                for (Rectangle rect : showedRects) {
                    rect.setFill(TetrisColor.O);
                }
                break;
            case "SShape":
                showedRects.add(rects[2][3]);
                showedRects.add(rects[3][3]);
                showedRects.add(rects[3][2]);
                showedRects.add(rects[4][2]);
                for (Rectangle rect : showedRects) {
                    rect.setFill(TetrisColor.S);
                }
                break;
            case "ZShape":
                showedRects.add(rects[1][2]);
                showedRects.add(rects[2][2]);
                showedRects.add(rects[2][3]);
                showedRects.add(rects[3][3]);
                for (Rectangle rect : showedRects) {
                    rect.setFill(TetrisColor.Z);
                }
                break;
            case "TShape":
                showedRects.add(rects[1][3]);
                showedRects.add(rects[2][3]);
                showedRects.add(rects[2][2]);
                showedRects.add(rects[3][3]);
                for (Rectangle rect : showedRects) {
                    rect.setFill(TetrisColor.T);
                }
        }
        for (Rectangle rect : showedRects) {
            rect.setVisible(true);
        }
    }

    private void clearAll() {
        for (Rectangle rect : showedRects) {
            rect.setVisible(false);
        }
        showedRects.clear();
    }
}
