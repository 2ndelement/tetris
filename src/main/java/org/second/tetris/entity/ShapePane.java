package org.second.tetris.entity;

import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.second.tetris.entity.Shape.ShapeFactory;
import org.second.tetris.entity.Shape.Tetromino;
import org.second.tetris.utils.TetrisColor;

import java.util.HashSet;
import java.util.Set;

/**
 * 块显示面板，用于显示一种块
 *
 * @author 吴晓鹏
 * @version 1.0
 */
public class ShapePane extends Pane {
    private final Rectangle[][] rects = new Rectangle[6][5];//显示单元
    private final Set<Rectangle> showedRects = new HashSet<>();//记录被显示格子
    private Tetromino shape = null;//当前显示形状

    /**
     * @param x    原点位置-X
     * @param y    原点位置-Y
     * @param size 内部网格尺寸 背景尺寸为{@code size}*6
     * @param arc  背景圆角度
     */
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

    /**
     * 获取一个当前显示形状的单例，不可用于生成{@link GameShape}
     *
     * @return 单例形状对象
     */
    public Tetromino getSingleShapeInstance() {
        return shape;
    }

    /**
     * 获取一个当前显示形状的新实例，可以用于生成{@link GameShape}
     *
     * @return 新实例形状对象
     */
    public Tetromino getNewShapeInstance() {
        return ShapeFactory.createNewInstance(shape);
    }

    /**
     * @return if 已经显示过形状 {@code true}},otherwise {@code false};
     */
    public boolean hasShowed() {
        return shape != null;
    }

    /**
     * 显示传入形状
     *
     * @param tetromino 待显示形状
     */
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

    /**
     * 清除显示形状
     */
    private void clearAll() {
        for (Rectangle rect : showedRects) {
            rect.setVisible(false);
        }
        showedRects.clear();
    }
}
