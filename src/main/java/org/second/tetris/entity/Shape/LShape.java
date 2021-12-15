package org.second.tetris.entity.Shape;

import org.second.tetris.entity.Cell;
/**
 * @author 吴晓鹏
 * @version 1.0
 * L形状的方块
 */
public class LShape extends Tetromino {
    public LShape() {
        cells[0] = new Cell(4, 18, TetrisColor.O);
        cells[1] = new Cell(5, 18, TetrisColor.O);
        cells[2] = new Cell(6, 18, TetrisColor.O);
        cells[3] = new Cell(6, 19, TetrisColor.O);
    }
    @Override
    //Todo:待测试
    public void spin() {
        Cell center = cells[1];
        for (Cell cell : cells) {
            cell.spin(center);
        }
    }
}
