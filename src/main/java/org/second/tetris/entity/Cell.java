package org.second.tetris.entity;

import javafx.scene.paint.Color;
/**
 * @author 吴晓鹏
 * @version 1.0
 * 单个格子类,4个格子组成一个{@link org.second.tetris.entity.Shape.Tetromino}
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
    public void moveDown(){
        this.y--;
    }
    public void moveLeft(){
        this.x--;
    }
    public void moveRight(){
        this.x++;
    }
    public void spin(Cell centerCell){
        int tempX = x;
        int tempY = y;
        x = centerCell.getX() - centerCell.getY() + tempY;
        y = centerCell.getX() + centerCell.getY() - tempX;
    }
}
