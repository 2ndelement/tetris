package org.second.tetris.entity.Shape;

import org.second.tetris.entity.Cell;

public class TShape extends Tetromino {
    public TShape() {
        cells[0] = new Cell(4, 18, TetrisColor.T);
        cells[1] = new Cell(5,18,TetrisColor.T);
        cells[2] = new Cell(6,18,TetrisColor.T);
        cells[3] = new Cell(5,19,TetrisColor.T);
    }

    @Override
    public void spin() {
        Cell center = cells[1];
        for(Cell cell : cells){
            cell.spin(center);
        }
    }
}
