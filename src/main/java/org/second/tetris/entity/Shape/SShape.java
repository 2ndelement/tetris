package org.second.tetris.entity.Shape;

import org.second.tetris.entity.Cell;
import org.second.tetris.utils.TetrisColor;

/**
 * @author 吴晓鹏
 * @version 1.0
 * S形状的方块
 */
public class SShape extends Tetromino {
    public SShape() {
        cells[0] = new Cell(4, 18, TetrisColor.S);
        cells[1] = new Cell(5, 18, TetrisColor.S);
        cells[2] = new Cell(5, 19, TetrisColor.S);
        cells[3] = new Cell(6, 19, TetrisColor.S);
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
