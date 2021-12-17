package org.second.tetris.entity.Shape;

import org.second.tetris.utils.TetrisColor;

/**
 * S形状的方块
 *
 * @author 吴晓鹏
 * @version 1.0
 */
public class SShape extends Tetromino {
    public SShape() {
        cells[0] = new Cell(4, 1, TetrisColor.S);
        cells[1] = new Cell(5, 1, TetrisColor.S);
        cells[2] = new Cell(5, 0, TetrisColor.S);
        cells[3] = new Cell(6, 0, TetrisColor.S);
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
