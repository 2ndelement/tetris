package org.second.tetris.entity;

import java.text.SimpleDateFormat;
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
    private String time;

    public int getScore() {
        return score;
    }

    public String getTime() {
        return time;
    }

    /**
     * 此处更改，将时间格式化。同时更改格式为字符串。
     * @param score
     */

    public ScoreRecord(int score) {
        this.score = score;
        Date date = new Date();
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        this.time = sbf.format(date);
    }

    /**
     * 在重载一个构造方法。
     * @param score
     * @param time
     */
    public ScoreRecord(int score,String time){
        this.score = score;
        this.time =time;
    }

    public String toString(){
        String strscore = String.valueOf(this.getScore());
        return strscore + " "+this.getTime();
    }
}
