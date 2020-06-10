package com.ta.coremolde.master.model.response;

public enum ErrorResponse {
    STOCK_INSUFFICIENT(400, "Stock insufficient"),
    FILE_EMPTY(400, "Image cannot be empty"),
    FILE_NAME_FAULT(400, "Cannot store file with relative path outside current directory"),
    ADDRESS_LIST_EMPTY(400, "Address list is empty"),
    PASSWORD_NOT_MATCH(400, "Old password not match"),
    UNAUTHORIZED_RESOURCE_ACCESS(403, "Access to resource not provided"),
    RESOURCE_NOT_FOUND(404, "Resource not found"),
    FAILED_TO_REMOVE_FILE(500, "Failed to remove file"),
    FAILED_TO_STORE_FILE(500, "Failed to store file");

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
