package org.second.tetris.entity;

import javafx.scene.shape.Rectangle;
import org.second.tetris.Tetris;
import org.second.tetris.entity.Shape.Cell;
import org.second.tetris.entity.Shape.Tetromino;

import java.util.Arrays;
import java.util.Iterator;

public class GameShape implements Iterable<Rectangle> {
    private Tetromino shape;
    private Rectangle[] rects = new Rectangle[4];

    public Tetromino getShape() {
        return shape;
    }

    public Rectangle getRect(int index) {
        return rects[index];
    }

    @Override
    public Iterator<Rectangle> iterator() {
        return Arrays.stream(rects).iterator();
    }

    public GameShape(Tetromino shape) {
        this.shape = shape;
        int i = 0;
        for (Cell cell : shape) {
            rects[i] = new Rectangle(cell.getX() * Tetris.SIZE, cell.getX() * Tetris.SIZE, Tetris.SIZE - 1, Tetris.SIZE - 1);
            rects[i++].setFill(cell.getColor());
        }
    }
}
