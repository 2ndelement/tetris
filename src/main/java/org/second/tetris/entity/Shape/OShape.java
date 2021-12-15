package org.second.tetris.entity.Shape;

import org.second.tetris.entity.Cell;

public class OShape extends Tetromino {
    public OShape() {
        cells[0] = new Cell(4, 18, TetrisColor.O);
        cells[1] = new Cell(5, 18, TetrisColor.O);
        cells[2] = new Cell(4, 19, TetrisColor.O);
        cells[3] = new Cell(5, 119, TetrisColor.O);
    }

    public void spin() {
    }
}
