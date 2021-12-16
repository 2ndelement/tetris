package org.second.tetris.entity.Shape;

/**
 * @author 吴晓鹏
 * @version 1.0
 * 方块抽象类,由4个{@link Cell}组成
 */
public abstract class Tetromino implements Cloneable {
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
