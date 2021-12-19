package org.second.tetris.entity;

import javafx.scene.effect.Glow;
import javafx.scene.effect.Lighting;
import javafx.scene.shape.Rectangle;
import org.second.tetris.Tetris;
import org.second.tetris.entity.Shape.Cell;
import org.second.tetris.entity.Shape.IShape;
import org.second.tetris.entity.Shape.OShape;
import org.second.tetris.entity.Shape.Tetromino;
import org.second.tetris.utils.TetrisColor;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 游戏内形状类，用于在游戏内显示以及处理形状
 *
 * @author 吴晓鹏
 * @version 1.0
 */
public class GameShape implements Iterable<Rectangle> {
    /**
     * 内部形状,存储逻辑位置
     **/
    private final Tetromino shape;
    /**
     * 形状显示单元
     */
    private final Rectangle[] rects = new Rectangle[4];
    private static final int R = 0;//右旋代码
    private static final int L = 1;//左旋代码
    public boolean lastSpin = false;
    /**
     * {@link IShape}的踢墙判断表,存储判断方案和偏移量
     */
    private static final Cell[][] ITabel = {
            {new Cell(0, 0), new Cell(-2, 0), new Cell(1, 0), new Cell(-2, -1), new Cell(1, 2)},
            {new Cell(0, 0), new Cell(2, 0), new Cell(-1, 0), new Cell(2, 1), new Cell(-1, -2)},
            {new Cell(0, 0), new Cell(-1, 0), new Cell(2, 0), new Cell(-1, 2), new Cell(2, -1)},
            {new Cell(0, 0), new Cell(1, 0), new Cell(-2, 0), new Cell(1, -2), new Cell(-2, 1)},
            {new Cell(0, 0), new Cell(2, 0), new Cell(-1, 0), new Cell(2, 1), new Cell(-1, -2)},
            {new Cell(0, 0), new Cell(-2, 0), new Cell(1, 0), new Cell(-2, -1), new Cell(1, 2)},
            {new Cell(0, 0), new Cell(1, 0), new Cell(-2, 0), new Cell(1, -2), new Cell(-2, 1)},
            {new Cell(0, 0), new Cell(-1, 0), new Cell(2, 0), new Cell(-1, 2), new Cell(2, -1)}
    };
    /**
     * T,J,L,Z,S的踢墙判断表,存储判断方案和偏移量
     */
    private static final Cell[][] otherTable = {
            {new Cell(0, 0), new Cell(-1, 0), new Cell(-1, +1), new Cell(0, -2), new Cell(-1, -2)},
            {new Cell(0, 0), new Cell(1, 0), new Cell(1, -1), new Cell(0, 2), new Cell(1, 2)},
            {new Cell(0, 0), new Cell(1, 0), new Cell(1, -1), new Cell(0, 2), new Cell(1, 2)},
            {new Cell(0, 0), new Cell(-1, 0), new Cell(-1, +1), new Cell(0, -2), new Cell(-1, -2)},
            {new Cell(0, 0), new Cell(1, 0), new Cell(1, +1), new Cell(0, -2), new Cell(1, -2)},
            {new Cell(0, 0), new Cell(-1, 0), new Cell(-1, -1), new Cell(0, 2), new Cell(-1, 2)},
            {new Cell(0, 0), new Cell(-1, 0), new Cell(-1, -1), new Cell(0, 2), new Cell(-1, 2)},
            {new Cell(0, 0), new Cell(1, 0), new Cell(1, +1), new Cell(0, -2), new Cell(1, -2)}
    };
    private int[][] mesh;
    public int status = 0;

    /**
     * 获取内部逻辑形状
     *
     * @return
     */
    public Tetromino getShape() {
        return shape;
    }

    /**
     * 获取指定索引的内部显示网格单元
     *
     * @param index 索引 0-3
     * @return 矩形单元
     */
    public Rectangle getRect(int index) {
        return rects[index];
    }

    /**
     * 下移形状
     *
     * @return if 成功 {@code true} else {@code false}}
     */
    public boolean moveDown() {
        shape.moveDown();
        if (isLegal()) {
            lastSpin = false;
            reDrawShape();
            return true;
        } else {
            shape.moveUp();
            return false;
        }
    }

    /**
     * 左移形状
     *
     * @return if 成功 {@code true} else {@code false}}
     */
    public boolean moveLeft() {
        shape.moveLeft();
        if (isLegal()) {
            lastSpin = false;
            reDrawShape();
            return true;
        } else {
            shape.moveRight();
            return false;
        }
    }

    /**
     * 右移形状
     *
     * @return if 成功 {@code true} else {@code false}}
     */
    public boolean moveRight() {
        shape.moveRight();
        if (isLegal()) {
            lastSpin = false;
            reDrawShape();
            return true;
        } else {
            shape.moveLeft();
            return false;
        }
    }

    /**
     * 左旋转(逆时针)
     *
     * @return if 成功 {@code true} else {@code false}}
     */
    public boolean lSpin() {
        if (shape instanceof OShape) {
            return false;
        }
        shape.lSpin();
        if (!KickWallHandle(L)) {
            shape.rSpin();
            return false;
        }
        reDrawShape();
        lastSpin = true;
        return true;
    }

    /**
     * 右旋转(顺时针)
     *
     * @return if 成功 {@code true} else {@code false}}
     */
    public boolean rSpin() {
        if (shape instanceof OShape) {
            return false;
        }
        shape.rSpin();
        if (!KickWallHandle(R)) {
            shape.lSpin();
            return false;
        }
        lastSpin = true;
        reDrawShape();
        return true;
    }

    /**
     * 处理踢墙
     *
     * @param direction 旋转方向
     * @return if 有合适旋转方案 {@code true} else {@code false}}
     */
    private boolean KickWallHandle(int direction) {
        Cell[][] kickTable = null;
        if (shape instanceof IShape) {
            kickTable = ITabel;
        } else {
            kickTable = otherTable;
        }
        switch (status) {
            case R:
                switch (status) {
                    case 0:
                        status = 1;
                        return tryTable(kickTable[0]);
                    case 1:
                        status = 2;
                        return tryTable(kickTable[2]);
                    case 2:
                        status = 3;
                        return tryTable(kickTable[4]);
                    case 3:
                        status = 0;
                        return tryTable(kickTable[6]);
                }
            case L:
                switch (status) {
                    case 0:
                        status = 3;
                        return tryTable(kickTable[7]);
                    case 1:
                        status = 0;
                        return tryTable(kickTable[1]);
                    case 2:
                        status = 1;
                        return tryTable(kickTable[3]);
                    case 3:
                        status = 2;
                        return tryTable(kickTable[5]);
                }
        }
        return false;
    }

    /**
     * @param table 偏移方案
     * @return if 偏移方案有合适方案 {@code true} else {@code false}}
     */
    private boolean tryTable(Cell[] table) {
        for (Cell cell : table) {
            move(cell);
            if (isLegal()) {
                return true;
            } else {
                deMove(cell);
            }
        }
        return false;
    }

    /**
     * 偏移
     *
     * @param cell 偏移量
     */
    private void move(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
        if (x < 0) {
            x = -x;
            while (x-- > 0) {
                shape.moveLeft();
            }
        } else {
            while (x-- > 0) {
                shape.moveRight();
            }
        }
        if (y < 0) {
            y = -y;
            while (y-- > 0) {
                shape.moveDown();
            }
        } else {
            while (y-- > 0) {
                shape.moveUp();
            }
        }

    }

    /**
     * 反向偏移
     *
     * @param cell 偏移量
     */
    private void deMove(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
        if (x < 0) {
            x = -x;
            while (x-- > 0) {
                shape.moveRight();
            }
        } else {
            while (x-- > 0) {
                shape.moveLeft();
            }
        }
        if (y < 0) {
            y = -y;
            while (y-- > 0) {
                shape.moveUp();
            }
        } else {
            while (y-- > 0) {
                shape.moveDown();
            }
        }

    }

    /**
     * 创建当前游戏形状的一个预览游戏形状
     *
     * @return 预览游戏形状
     */
    public GameShape createPrew() {
        GameShape prewShape = new GameShape(shape.clone(), mesh);
        for (Rectangle rect : prewShape) {
            rect.setOpacity(TetrisColor.Opacity);
            rect.setEffect(new Glow(10));
            rect.setFill(TetrisColor.PREWIEW);
        }
        while (prewShape.moveDown()) ;
        return prewShape;
    }

    /**
     * 判断当前状态是否合法,即有无与已锚定块重合和卡墙
     *
     * @return if 合法 {@code true} else {@code false}；
     */
    private boolean isLegal() {
        for (Cell cell : shape) {
            int x = cell.getX();
            int y = cell.getY();
            if (x < 0 || x > Tetris.XMAX - 1 || y < 0 || y > Tetris.YMAX - 1 || mesh[y][x] == 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 重绘形状,将逻辑位置显示
     */
    private void reDrawShape() {
        int i = 0;
        for (Cell cell : shape) {
            int x = cell.getX();
            int y = cell.getY();
            rects[i].setX(Tetris.LEFT + x * Tetris.SIZE);
            rects[i++].setY(Tetris.TOP + y * Tetris.SIZE);
        }
    }

    @Override
    public Iterator<Rectangle> iterator() {
        return Arrays.stream(rects).iterator();
    }

    public GameShape(Tetromino shape, int[][] mesh) {
        this.shape = shape;
        this.mesh = mesh;
        int i = 0;
        for (Cell cell : shape) {
            rects[i] = new Rectangle(Tetris.LEFT + cell.getX() * Tetris.SIZE, Tetris.TOP + cell.getY() * Tetris.SIZE, Tetris.SIZE - 1, Tetris.SIZE - 1);
            rects[i].setEffect(new Lighting());
            rects[i++].setFill(shape.getColor());
        }
    }
}
