package org.second.tetris.entity.Shape;

import org.second.tetris.utils.TetrisColor;

/**
 * Z形状的方块
 *
 * @author 吴晓鹏
 * @version 1.0
 */
public class ZShape extends Tetromino {
    public ZShape() {
        cells[0] = new Cell(4, 0, TetrisColor.Z);
        cells[1] = new Cell(5, 0, TetrisColor.Z);
        cells[2] = new Cell(5, 1, TetrisColor.Z);
        cells[3] = new Cell(6, 1, TetrisColor.Z);
    }

    @Override
    public void rSpin() {
        Cell center = cells[2];
        for (Cell cell : cells) {
            cell.rSpin(center);
        }
    }

    @Override
    public void lSpin() {
        Cell center = cells[2];
        for (Cell cell : cells) {
            cell.lSpin(center);
        }
    }
}
