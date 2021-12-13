package com.company;

public class FoldInstruction {
    private final FoldType foldType;
    private final int value;

    public FoldInstruction(String type, int value) {
        this.foldType = type.equals("x") ? FoldType.FOLD_X : FoldType.FOLD_Y;
        this.value = value;
    }

    public FoldType getFoldType() {
        return foldType;
    }

    public int getValue() {
        return value;
    }

    public enum FoldType {
        FOLD_Y,
        FOLD_X
    }
}
