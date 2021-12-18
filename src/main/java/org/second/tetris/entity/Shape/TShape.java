package org.second.tetris.entity.Shape;

import org.second.tetris.utils.TetrisColor;

/**
 * T形状的方块
 *
 * @author 吴晓鹏
 * @version 1.0
 */
public class TShape extends Tetromino {
    public TShape() {
        cells[0] = new Cell(4, 1);
        cells[1] = new Cell(5, 1);
        cells[2] = new Cell(5, 0);
        cells[3] = new Cell(6, 1);
        color = TetrisColor.T;
    }

    @Override
    public void rSpin() {
        Cell center = cells[1];
        for (Cell cell : cells) {
            cell.rSpin(center);
        }
    }

    @Override
    public void lSpin() {
        Cell center = cells[1];
        for (Cell cell : cells) {
            cell.lSpin(center);
        }
    }
}
