package org.second.tetris.entity.Shape;

import javafx.scene.paint.Color;

/**
 * 单个格子类,4个格子组成一个{@link org.second.tetris.entity.Shape.Tetromino}
 *
 * @author 吴晓鹏
 * @version 1.0
 */
public class Cell {
    private int x;
    private int y;
    private Color color;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cell(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void moveUp() {
        this.y--;
    }

    public void moveDown() {
        this.y++;
    }

    public void moveLeft() {
        this.x--;
    }

    public void moveRight() {
        this.x++;
    }

    public void lSpin(Cell centerCell) {
        int tempX = x;
        int tempY = y;
        x = centerCell.getX() - centerCell.getY() + tempY;
        y = centerCell.getX() + centerCell.getY() - tempX;
    }

    public void rSpin(Cell centerCell) {
        int tempX = x;
        int tempY = y;
        x = centerCell.getX() + centerCell.getY() - tempY;
        y = centerCell.getY() - centerCell.getX() + tempX;
    }
}
