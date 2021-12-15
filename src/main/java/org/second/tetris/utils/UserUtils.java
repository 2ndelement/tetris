package org.second.tetris.utils;

import java.util.Random;

/**
 * @author 吴晓鹏
 * @version 1.0
 * 用于为用户相关操作提供帮助的工具类
 */
//Todo 待实现存储读取用户信息和记录，暂定存储数据格式为Json
public class UserUtils {
    /**
     * 随机生成一个用户名;用户名规则 Tetris_[0-9]{5};
     *
     * @return 随机生成的用户名
     */
    public static String generateName() {
        Random random = new Random();
        StringBuilder name = new StringBuilder("Tetris_");
        int i = 5;
        while (i-- > 0) {
            name.append(random.nextInt(10));
        }
        return name.toString();
    }
}
