package com.ta.coremolde.shop.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private long totalPrice;
    private long totalShipmentPrice;
    private long totalPaymentPrice;
    private String courier;
    private String address;
    private int originId;
    private String originCity;
    private int destinationId;
    private String destinationCity;
    private String recipient;
    private String recipientPhone;
}
