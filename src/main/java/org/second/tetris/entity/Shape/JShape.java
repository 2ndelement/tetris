package org.second.tetris.entity.Shape;

import org.second.tetris.utils.TetrisColor;

/**
 * J形状的方块
 *
 * @author 吴晓鹏
 * @version 1.0
 */
public class JShape extends Tetromino {
    public JShape() {
        cells[0] = new Cell(4, 19, TetrisColor.O);
        cells[1] = new Cell(4, 18, TetrisColor.O);
        cells[2] = new Cell(5, 18, TetrisColor.O);
        cells[3] = new Cell(6, 18, TetrisColor.O);
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
