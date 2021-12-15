package org.second.tetris.entity;

import javafx.scene.paint.Color;

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
}
