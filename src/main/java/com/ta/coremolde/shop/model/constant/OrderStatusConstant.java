package com.ta.coremolde.shop.model.constant;

public enum OrderStatusConstant {
    WAITING_FOR_PAYMENT("WAITING_FOR_PAYMENT"),
    WAITING_FOR_PAYMENT_CONFIRMATION("WAITING_FOR_PAYMENT_CONFIRMATION"),
    PAYMENT_ACCEPTED("PAYMENT_ACCEPTED"),
    PAYMENT_REJECTED("PAYMENT_REJECTED"),
    SHIPMENT_IN_PROGRESS("SHIPMENT_IN_PROGRESS"),
    ORDER_COMPLETE("ORDER_COMPLETE"),
    CANCELLED("CANCELLED");

    private String status;

    OrderStatusConstant(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
