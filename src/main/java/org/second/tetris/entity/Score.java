package org.second.tetris.entity;

public class Score {
    private int clearLineCount;
    private boolean isTSpin;

    public Score(int count, boolean isTSpin) {
        clearLineCount = count;
        this.isTSpin = isTSpin;
    }

    public Score(int count) {
        clearLineCount = count;
    }

    public boolean isSpecail() {
        if (isTSpin || clearLineCount == 4) {
            return true;
        } else {
            return false;
        }
    }

    public int getClearLineCount() {
        return clearLineCount;
    }

    public boolean isTSpin() {
        return isTSpin;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Score{");
        sb.append("clearLineCount=").append(clearLineCount);
        sb.append(", isTSpin=").append(isTSpin);
        sb.append('}');
        return sb.toString();
    }
}
