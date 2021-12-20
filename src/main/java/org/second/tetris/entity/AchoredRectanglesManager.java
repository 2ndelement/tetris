package org.second.tetris.entity;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.second.tetris.Tetris;
import org.second.tetris.entity.Shape.Cell;
import org.second.tetris.entity.Shape.TShape;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 管理锚定的形状,进行锚定、消除和各类处理
 *
 * @author 吴晓鹏
 * @version 1.0
 */
public class AchoredRectanglesManager {
    private final Rectangle[][] rects = new Rectangle[Tetris.YMAX][Tetris.XMAX];
    private final Pane game;
    private final int[][] MESH;
    private final Set<Integer> erasedLines = new HashSet<>();

    public Score anchorShape(GameShape shape) {
        addRectangles(shape);
        return computeScore(shape);
    }

    /**
     * 将游戏形状锚定
     *
     * @param shape 游戏形状
     */
    private void addRectangles(GameShape shape) {
        int i = 0;
        for (Cell cell : shape.getShape()) {
            MESH[cell.getY()][cell.getX()] = 1;
            rects[cell.getY()][cell.getX()] = shape.getRect(i++);
        }
    }

    /**
     * 检测并完成消除,同时计算得分
     *
     * @param shape 待锚定游戏形状
     * @return 得分
     */
    private Score computeScore(GameShape shape) {
        Score score;
        Set<Integer> mayEraseLines = new HashSet<>();
        for (Cell cell : shape.getShape()) {
            mayEraseLines.add(cell.getY());
        }
        for (int y : mayEraseLines) {
            if (isLineFull(y)) {
                erasedLines.add(y);
            }
        }
        score = new Score(erasedLines.size(), isTSpin(shape));
        for (int line : erasedLines) {
            eraseLine(line);
        }
        if (erasedLines.size() != 0) {
            dropLines(Collections.max(erasedLines) + 1);
        }
        erasedLines.clear();
        System.out.println(score);
        return score;
    }

    private boolean isTSpin(GameShape shape) {
        if (!(shape.getShape() instanceof TShape) || !shape.lastSpin) {
            return false;
        }
        int count = 0;
        Cell cell = shape.getShape().getCell(1);
        Cell[] cells = {new Cell(cell.getX() - 1, cell.getY() - 1), new Cell(cell.getX() + 1, cell.getY() + 1),
                new Cell(cell.getX() - 1, cell.getY() + 1), new Cell(cell.getX() + 1, cell.getY() - 1)};
        for (Cell check : cells) {
            if (check.getX() < 0 || check.getX() >= Tetris.XMAX || check.getY() >= Tetris.YMAX || MESH[check.getY()][check.getX()] == 1) {
                count++;
            }
        }
        if (count >= 3) {
            return true;
        }
        return false;
    }

    /**
     * 获取最高有块坐标
     *
     * @return
     */
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

    private void dropLines(int dropFrom) {
        int topLine = getTopLine();
        for (int line = dropFrom; line >= topLine; line--) {
            if (!erasedLines.contains(line))
                dropLine(line);
        }
    }

    private void dropLine(int y) {
        while (y + 1 < rects.length && !hasRect(y + 1)) {
            MESH[y + 1] = MESH[y];
            MESH[y] = new int[MESH[y].length];
            for (Rectangle rect : rects[y]) {
                if (rect != null) {
                    rect.setY(rect.getY() + Tetris.SIZE);
                }
            }
            rects[y + 1] = rects[y];
            rects[y] = new Rectangle[Tetris.XMAX];
            y++;
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
