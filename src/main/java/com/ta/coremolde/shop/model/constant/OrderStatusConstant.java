package com.ta.coremolde.shop.model.constant;

public enum OrderStatusConstant {
    IN_PROGRESS("IN_PROGRESS"),
    PAYMENT_ACCEPTED("PAYMENT_ACCEPTED"),
    CANCELLED("CANCELLED");

    private String status;

    OrderStatusConstant(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
