package org.second.tetris.entity.Shape;

import org.second.tetris.entity.Cell;
import org.second.tetris.utils.TetrisColor;

public class ZShape extends Tetromino {
    public ZShape() {
        cells[0] = new Cell(4, 19, TetrisColor.Z);
        cells[1] = new Cell(5, 19, TetrisColor.Z);
        cells[2] = new Cell(5, 18, TetrisColor.Z);
        cells[3] = new Cell(6, 18, TetrisColor.Z);
    }

    @Override
    //Todo:待测试
    public void spin() {
        Cell center = cells[2];
        for (Cell cell : cells) {
            cell.spin(center);
        }
    }
}
