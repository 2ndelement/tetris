package org.second.tetris.entity.Shape;

import org.second.tetris.utils.TetrisColor;

/**
 * O形状的方块
 *
 * @author 吴晓鹏
 * @version 1.0
 */
public class OShape extends Tetromino {
    public OShape() {
        cells[0] = new Cell(4, 1);
        cells[1] = new Cell(5, 1);
        cells[2] = new Cell(4, 0);
        cells[3] = new Cell(5, 0);
        color = TetrisColor.O;
    }

    @Override
    public void rSpin() {
        //该形状不旋转
    }

    @Override
    public void lSpin() {
        //该形状不旋转
    }
}
