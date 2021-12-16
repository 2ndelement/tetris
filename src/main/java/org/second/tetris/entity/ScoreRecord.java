package org.second.tetris.entity;

import java.util.Date;

/**
 * 得分类,存储得分{@code score}和记录时间{@code time}
 *
 * @author 吴晓鹏
 * @version 1.0
 * @see User
 */
public class ScoreRecord {
    private int score;
    private Date time;

    public int getScore() {
        return score;
    }

    public Date getTime() {
        return time;
    }

    public ScoreRecord(int score) {
        this.score = score;
        this.time = new Date(System.currentTimeMillis());
    }
}
