package org.second.tetris.entity.Shape;

import org.second.tetris.entity.Cell;

/**
 * @author 吴晓鹏
 * @version 1.0
 * I形状的方块
 */
public class IShape extends Tetromino {
    public IShape() {
        cells[0] = new Cell(3, 19, TetrisColor.I);
        cells[1] = new Cell(4, 19, TetrisColor.I);
        cells[2] = new Cell(5, 19, TetrisColor.I);
        cells[3] = new Cell(6, 19, TetrisColor.I);
    }

    @Override
    //Todo:IShape的spin()待测试
    public void spin() {
        Cell center = cells[2];
        for (Cell cell : cells) {
            cell.spin(center);
        }
    }
}
