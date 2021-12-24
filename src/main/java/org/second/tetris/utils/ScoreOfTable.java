package org.second.tetris.utils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author 孙士杰
 */
public class ScoreOfTable {
    private SimpleIntegerProperty score;
    private SimpleStringProperty date;

    public ScoreOfTable(int score, String date) {
        this.score = new SimpleIntegerProperty(score);
        this.date = new SimpleStringProperty(date);
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score = new SimpleIntegerProperty(score);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }
}
