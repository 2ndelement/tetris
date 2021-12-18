package org.second.tetris.entity;

import javafx.scene.shape.Rectangle;
import org.second.tetris.Tetris;
import org.second.tetris.entity.Shape.Cell;
import org.second.tetris.entity.Shape.Tetromino;

import java.util.Arrays;
import java.util.Iterator;

public class GameShape implements Iterable<Rectangle> {
    private Tetromino shape;
    private Rectangle[] rects = new Rectangle[4];

    public Tetromino getShape() {
        return shape;
    }

    public Rectangle getRect(int index) {
        return rects[index];
    }

    public boolean moveDown(int[][] mesh) {
        shape.moveDown();
        if (isMoveLegal(mesh)) {
            RedrawShape();
            return true;
        } else {
            shape.moveUp();
            return false;
        }
    }

    public boolean moveLeft(int[][] mesh) {
        shape.moveLeft();
        if (isMoveLegal(mesh)) {
            RedrawShape();
            return true;
        } else {
            shape.moveRight();
            return false;
        }
    }

    public boolean moveRight(int[][] mesh) {
        shape.moveRight();
        if (isMoveLegal(mesh)) {
            RedrawShape();
            return true;
        } else {
            shape.moveLeft();
            return false;
        }
    }

    public boolean lSpin(int[][] mesh) {
        shape.lSpin();
        if (isSpinLegal(mesh)) {
            KickWallHandle();
            RedrawShape();
            return true;
        } else {
            shape.rSpin();
            return false;
        }
    }

    public boolean rSpin(int[][] mesh) {
        shape.rSpin();
        if (isSpinLegal(mesh)) {
            KickWallHandle();
            RedrawShape();
            return true;
        } else {
            shape.lSpin();
            return false;
        }
    }

    private void KickWallHandle() {
        int Lmove = 0;
        int Rmove = 0;
        for (Cell cell : shape) {
            int x = cell.getX();
            if (x < 0) {
                Rmove++;
            }
            if (x > Tetris.XMAX - 1) {
                Lmove++;
            }
        }
        while (Rmove-- > 0) {
            shape.moveRight();
        }
        while (Lmove-- > 0) {
            shape.moveLeft();
        }
    }

    public GameShape createPrew(int[][] mesh) {
        GameShape prewShape = new GameShape(shape.clone());
        for (Rectangle rect : prewShape) {
            rect.setOpacity(0.5);
        }
        while (prewShape.moveDown(mesh)) ;
        return prewShape;
    }

    private boolean isSpinLegal(int[][] mesh) {
        for (Cell cell : shape) {
            int x = cell.getX();
            int y = cell.getY();
            if ((x >= 0 && x <= Tetris.XMAX - 1 && y > 0 && y <= Tetris.YMAX - 1 && mesh[y][x] == 1) || y > Tetris.YMAX - 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isMoveLegal(int[][] mesh) {
        for (Cell cell : shape) {
            int x = cell.getX();
            int y = cell.getY();
            if (x < 0 || x > Tetris.XMAX - 1 || y < 0 || y > Tetris.YMAX - 1 || mesh[y][x] == 1) {
                return false;
            }
        }
        return true;
    }

    private void RedrawShape() {
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

    public GameShape(Tetromino shape) {
        this.shape = shape;
        int i = 0;
        for (Cell cell : shape) {
            rects[i] = new Rectangle(Tetris.LEFT + cell.getX() * Tetris.SIZE, cell.getY() * Tetris.SIZE, Tetris.SIZE - 1, Tetris.SIZE - 1);
            rects[i++].setFill(cell.getColor());
        }
    }
}
