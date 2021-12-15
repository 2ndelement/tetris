package org.second.tetris.entity;

import org.second.tetris.utils.UserUtils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 用户类,存储用户相关信息;包括用户名{@code name},用户得分记录{@code records};
 *
 * @author 吴晓鹏
 * @version 1.0
 * @see UserUtils
 * @see ScoreRecord
 */
public class User implements Iterable<ScoreRecord> {
    private String name;
    private final ArrayList<ScoreRecord> records = new ArrayList<>();

    public void addRecord(int score) {
        records.add(new ScoreRecord(score));
    }

    @Override
    public Iterator<ScoreRecord> iterator() {
        return records.iterator();
    }

    public String getName() {
        return name;
    }

    /**
     * 获取最高的得分记录
     *
     * @return 最高得分记录
     */
    public ScoreRecord getMaxScoreRecord() {
        if (records.size() == 0) {
            return null;
        }
        ScoreRecord maxScoreRecord = records.get(1);
        for (ScoreRecord record : records) {
            maxScoreRecord = maxScoreRecord.getScore() > record.getScore() ? maxScoreRecord : record;
        }
        return maxScoreRecord;
    }

    /**
     * 指定用户名创建用户
     *
     * @param name 用户名
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * 随机用户名创建用户
     */
    public User() {
        this.name = UserUtils.generateName();
    }
}
