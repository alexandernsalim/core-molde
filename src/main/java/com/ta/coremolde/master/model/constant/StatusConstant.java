package com.ta.coremolde.master.model.constant;

public enum StatusConstant {
    WAITING(0),
    ACCEPT(1),
    REJECT(2);

    private int value;

    StatusConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
