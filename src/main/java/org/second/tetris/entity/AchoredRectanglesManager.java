package org.second.tetris.entity;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.second.tetris.Tetris;
import org.second.tetris.entity.Shape.Cell;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AchoredRectanglesManager {
    private Rectangle[][] rects = new Rectangle[Tetris.YMAX][Tetris.XMAX];
    private Pane game;
    private int[][] MESH;
    private Set<Integer> erasedLines = new HashSet<>();

    public int anchorShape(GameShape shape) {
        addRectangles(shape);
        return computeScore(shape);
    }

    private void addRectangles(GameShape shape) {
        int i = 0;
        for (Cell cell : shape.getShape()) {
            MESH[cell.getY()][cell.getX()] = 1;
            rects[cell.getY()][cell.getX()] = shape.getRect(i++);
        }
    }

    private int computeScore(GameShape shape) {
        int score = 0;
        Set<Integer> mayEraseLines = new HashSet<>();
        for (Cell cell : shape.getShape()) {
            mayEraseLines.add(cell.getY());
        }
        for (int y : mayEraseLines) {
            if (isLineFull(y)) {
                eraseLine(y);
                erasedLines.add(y);
            }
        }
        if (erasedLines.size() != 0) {
            int beginLine = Collections.min(erasedLines) - 1;
            int moveLength = erasedLines.size();
            dropLines(beginLine, moveLength);
            erasedLines.clear();
            switch (erasedLines.size()) {
                case 1:
                    score = 40;
                    break;
                case 2:
                    score = 100;
                    break;
                case 3:
                    score = 300;
                    break;
                case 4:
                    score = 1200;
                    break;
            }
            return score;
        }
        return 0;
    }

    private int getTopLine() {
        for (int y = 0; y < rects.length; y++) {
            if (hasRect(y)) {
                return y;
            }
        }
        return Tetris.YMAX;
    }

    private boolean hasRect(int y) {
        for (int i = 0; i < rects[y].length; i++) {
            if (rects[y][i] != null) {
                return true;
            }
        }
        return false;
    }

    private void eraseLine(int y) {
        for (int i = 0; i < rects[y].length; i++) {
            if (rects[y][i] != null) {
                game.getChildren().remove(rects[y][i]);
                rects[y][i] = null;
            }
            MESH[y][i] = 0;
        }
    }

    private boolean isLineFull(int y) {

        if (y >= Tetris.YMAX || y < 0) {
            throw new IndexOutOfBoundsException(y + "超出有效范围");
        }
        for (int i = 0; i < rects[y].length; i++) {
            if (MESH[y][i] == 0) {
                return false;
            }
        }
        return true;
    }

    public AchoredRectanglesManager(Pane game, int[][] MESH) {
        this.game = game;
        this.MESH = MESH;
    }

    private void dropLines(int dropFrom, int len) {
        int dropTo = getTopLine();
        for (int line = dropFrom; line >= dropTo; line--) {
            MESH[line + len] = MESH[line];
            MESH[line] = new int[MESH[line].length];
            for (Rectangle rect : rects[line]) {
                if (rect != null) {
                    rect.setY(rect.getY() + len * Tetris.SIZE);
                }
            }
            rects[line + len] = rects[line];
            rects[line] = new Rectangle[Tetris.XMAX];
        }
    }

    public void upLines(int height) {
        int topLine = getTopLine();
        for (int line = topLine; line < rects.length; line++) {
            if (line - height >= 0) {
                MESH[line - height] = MESH[line];
                rects[line - height] = rects[line];
                for (Rectangle rect : rects[line]) {
                    if (rect != null) {
                        rect.setY(rect.getY() - height * Tetris.SIZE);
                    }
                }
            } else {
                Tetris.isOver = true;
                eraseLine(line);
            }
            MESH[line] = new int[MESH[line].length];
            rects[line] = new Rectangle[Tetris.XMAX];

        }
    }
}
