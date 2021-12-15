package org.second.tetris.entity.Shape;

import org.second.tetris.entity.Cell;

/**
 * @author 吴晓鹏
 * @version 1.0
 * 方块抽象类,由4个{@link Cell}组成
 */
public abstract class Tetromino {
    protected Cell[] cells = new Cell[4];

    /**
     * 下移该块
     */
    public void moveDown() {
        for (Cell cell : cells) {
            cell.moveDown();
        }
    }

    /**
     * 左移该块
     */
    public void moveLeft() {
        for (Cell cell : cells) {
            cell.moveLeft();
        }

    }

    /**
     * 右移该块
     */
    public void moveRight() {
        for (Cell cell : cells) {
            cell.moveRight();
        }
    }

    /**
     * 方块的旋转
     */
    public abstract void spin();

}
