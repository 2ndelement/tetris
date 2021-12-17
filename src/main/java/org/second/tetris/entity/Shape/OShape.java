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
        cells[0] = new Cell(4, 1, TetrisColor.O);
        cells[1] = new Cell(5, 1, TetrisColor.O);
        cells[2] = new Cell(4, 0, TetrisColor.O);
        cells[3] = new Cell(5, 0, TetrisColor.O);
    }

    public void spin() {
        //该形状无需旋转
    }
}
