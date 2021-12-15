package org.second.tetris.entity.Shape;

import org.second.tetris.entity.Cell;

public abstract class Tetromino {
    protected Cell[] cells = new Cell[4];

    public void moveDown() {
        for (Cell cell : cells) {
            cell.moveDown();
        }
    }

    public void moveLeft() {
        for (Cell cell : cells) {
            cell.moveLeft();
        }

    }

    public void moveRight() {
        for (Cell cell : cells) {
            cell.moveRight();
        }
    }

    public abstract void spin();

}
