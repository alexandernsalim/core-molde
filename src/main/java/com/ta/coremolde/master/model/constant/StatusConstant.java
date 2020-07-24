package com.ta.coremolde.master.model.constant;

public enum StatusConstant {
    WAITING(0),
    ACCEPT(1),
    COMPLETE(2),
    REJECT(3);

    private int value;

    StatusConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
