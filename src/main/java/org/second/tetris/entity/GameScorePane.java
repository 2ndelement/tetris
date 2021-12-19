package org.second.tetris.entity;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameScorePane extends Pane {
    private static final int TOPLEVEL = 10;
    private int score = 0;
    private boolean isBTB = false;
    private int specialCount = 0;
    private int combo = 0;
    private int level = 1;
    private int clearLinesNum = 0;
    private final Text scoreText = new Text();

    public GameScorePane(int x, int y, int size, int arc) {
        super();
        setLayoutX(x);
        setLayoutY(y);
        scoreText.setFont(new Font("Consolas", size));
        scoreText.setText("Level:" + level + " Score:" + score);
        getChildren().add(scoreText);
    }

    public long speed() {
        return (long) (1000 * Math.pow(0.8 - (level - 1) * 0.007, level - 1));
    }

    public void add(Score singelScore) {
        score += 5 * (combo - 1 < 0 ? combo : combo - 1) * level;
        if (singelScore.isSpecail()) {
            specialCount++;
        } else {
            isBTB = false;
            specialCount = 0;
        }
        if (specialCount >= 2) {
            isBTB = true;
        }
        switch (singelScore.getClearLineCount()) {
            case 0:
                if (singelScore.isTSpin()) {
                    score += 40 * level * (isBTB ? 1.5 : 1);
                }
                combo = 0;
                break;
            case 1:
                if (singelScore.isTSpin()) {
                    score += 80 * level * (isBTB ? 1.5 : 1);
                } else {
                    score += 10 * level;
                }
                combo++;
                break;
            case 2:
                if (singelScore.isTSpin()) {
                    score += 120 * level * (isBTB ? 1.5 : 1);
                } else {
                    score += 30 * level;
                }
                combo++;

                break;
            case 3:
                if (singelScore.isTSpin()) {
                    score += 150 * level * (isBTB ? 1.5 : 1);
                } else {
                    score += 50 * level;
                }
                combo++;
                break;
            case 4:
                score += 80 * level * (isBTB ? 1.5 : 1);
                combo++;
        }
        clearLinesNum += singelScore.getClearLineCount();
        if (level <= TOPLEVEL) {
            level = clearLinesNum / 10 + 1;
        }
        scoreText.setText("Level:" + level + " Score:" + score);
    }

}
