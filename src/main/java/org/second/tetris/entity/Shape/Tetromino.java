package org.second.tetris.entity.Shape;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author 吴晓鹏
 * @version 1.0
 * 方块抽象类,由4个{@link Cell}组成
 */
public abstract class Tetromino implements Cloneable, Iterable<Cell> {
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
     * 旋转该块
     */
    public abstract void spin();

    public Cell getCell(int index) {
        return cells[index];
    }

    @Override
    public Iterator<Cell> iterator() {
        return Arrays.stream(cells).iterator();
    }

    /**
     * 克隆一个相同状态完全相同的块,可用于预演方块变化
     *
     * @return 状态相同的一个块
     * @throws CloneNotSupportedException 不支持克隆错误
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
