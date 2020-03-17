package com.ta.coremolde.model.exception;

public class MoldeException extends RuntimeException {
    private int code;

    public MoldeException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
