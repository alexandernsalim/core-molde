package com.ta.coremolde.master.model.response;

public enum ErrorResponse {
    UNAUTHORIZED_RESOURCE_ACCESS(401, "Access to resource not provided"),
    RESOURCE_NOT_FOUND(403, "Resource not found");

    private int code;
    private String message;

    ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
