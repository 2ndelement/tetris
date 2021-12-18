package org.second.tetris.entity;

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

public class GameShape implements Iterable<Rectangle> {
    private Tetromino shape;
    private Rectangle[] rects = new Rectangle[4];
    private static final int R = 0;
    private static final int L = 1;
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
    private static int status = 0;

    public Tetromino getShape() {
        return shape;
    }

    public Rectangle getRect(int index) {
        return rects[index];
    }

    public boolean moveDown() {
        shape.moveDown();
        if (isLegal()) {
            reDrawShape();
            return true;
        } else {
            shape.moveUp();
            return false;
        }
    }

    public boolean moveLeft() {
        shape.moveLeft();
        if (isLegal()) {
            reDrawShape();
            return true;
        } else {
            shape.moveRight();
            return false;
        }
    }

    public boolean moveRight() {
        shape.moveRight();
        if (isLegal()) {
            reDrawShape();
            return true;
        } else {
            shape.moveLeft();
            return false;
        }
    }

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
        return true;
    }

    public boolean rSpin() {
        if (shape instanceof OShape) {
            return false;
        }
        shape.rSpin();
        if (!KickWallHandle(R)) {
            shape.lSpin();
            return false;
        }
        reDrawShape();
        return true;
    }

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

    public GameShape createPrew() {
        GameShape prewShape = new GameShape(shape.clone(), mesh);
        for (Rectangle rect : prewShape) {
            rect.setOpacity(TetrisColor.Opacity);
        }
        while (prewShape.moveDown()) ;
        return prewShape;
    }

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

    private void reDrawShape() {
        int i = 0;
        for (Cell cell : shape) {
            int x = cell.getX();
            int y = cell.getY();
            rects[i].setX(Tetris.LEFT + x * Tetris.SIZE);
            rects[i++].setY(y * Tetris.SIZE);
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
            rects[i] = new Rectangle(Tetris.LEFT + cell.getX() * Tetris.SIZE, cell.getY() * Tetris.SIZE, Tetris.SIZE - 1, Tetris.SIZE - 1);
            rects[i].setEffect(new Lighting());
            rects[i++].setFill(shape.getColor());
        }
    }
}
