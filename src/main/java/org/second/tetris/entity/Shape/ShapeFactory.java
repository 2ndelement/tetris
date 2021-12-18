package org.second.tetris.entity.Shape;

import java.util.Random;

/**
 * 形状制造工厂
 *
 * @author 吴晓鹏
 * @version 1.0
 */
public class ShapeFactory {
    private static final int BAG_SIZE = 7;//形状包尺寸
    private static int remainNum = BAG_SIZE;//记录未调取的形状数
    private static final Random codeGenerator = new Random();
    private static final boolean[] codeTable = new boolean[BAG_SIZE];//形状调取状态记录表
    private static final Tetromino[] instances = {new IShape(), new OShape(), new LShape(), new JShape(), new ZShape(), new SShape(), new TShape()};

    /**
     * 根据Tetris标准包规则生成形状
     *
     * @return 一个形状
     */
    public static Tetromino nextShape() {
        //包内剩余形状数为0时,重置记录数组
        if (remainNum == 0) {
            resetCodeTable();
        }
        int newCode = codeGenerator.nextInt(7);
        while (codeTable[newCode]) {
            newCode = codeGenerator.nextInt(7);
        }
        codeTable[newCode] = true;
        remainNum--;
        return createShape(newCode);
    }

    private static void resetCodeTable() {
        for (int i = 0; i < BAG_SIZE; i++) {
            codeTable[i] = false;
        }
        remainNum = BAG_SIZE;
    }

    /**
     * 根据传入代号创建一个形状;对应关系如下<br>
     * 0-I; 1-O; 2-L; 3-J; 4-Z; 5-S; 6-T;
     *
     * @param code 目标形状块的代号
     * @return if code legal {@code shape};<br>
     * otherwise {@code null}
     */
    private static Tetromino createShape(int code) {
        switch (code) {
            case 0:
                return new IShape();
            case 1:
                return new OShape();
            case 2:
                return new LShape();
            case 3:
                return new JShape();
            case 4:
                return new ZShape();
            case 5:
                return new SShape();
            case 6:
                return new TShape();
        }
        return null;
    }

    public static Tetromino makeNewOne(Tetromino old) {
        String[] names = old.getClass().getName().split("\\.");
        String className = names[names.length - 1];
        switch (className) {
            case "IShape":
                return instances[0];
            case "OShape":
                return instances[1];
            case "LShape":
                return instances[2];
            case "JShape":
                return instances[3];
            case "ZShape":
                return instances[4];
            case "SShape":
                return instances[5];
            case "TShape":
                return instances[6];
        }
        return null;
    }
}
