package org.second.tetris.entity.Shape;

import org.second.tetris.utils.TetrisColor;

/**
 * I形状的方块
 *
 * @author 吴晓鹏
 * @version 1.0
 */
public class IShape extends Tetromino {
    private int status = 0;

    public IShape() {
        cells[0] = new Cell(3, 1);
        cells[1] = new Cell(4, 1);
        cells[2] = new Cell(5, 1);
        cells[3] = new Cell(6, 1);
        color = TetrisColor.I;
    }

    @Override
    public void rSpin() {
        Cell center = cells[1];
        for (Cell cell : cells) {
            cell.rSpin(center);
        }
        switch (status) {
            case 0:
                moveRight();
                status = 1;
                break;
            case 1:
                moveDown();
                status = 2;
                break;
            case 2:
                moveLeft();
                status = 3;
                break;
            case 3:
                moveUp();
                status = 0;
        }
    }

    @Override
    public void lSpin() {
        Cell center = cells[1];
        for (Cell cell : cells) {
            cell.lSpin(center);
        }
        switch (status) {
            case 0:
                moveDown();
                status = 4;
                break;
            case 1:
                moveLeft();
                status = 1;
                break;
            case 2:
                moveUp();
                status = 1;
                break;
            case 3:
                moveRight();
                status = 2;
        }
    }
}
