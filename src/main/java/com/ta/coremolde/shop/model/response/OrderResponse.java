package com.ta.coremolde.shop.model.response;

import com.ta.coremolde.shop.model.entity.Shipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Integer id;
    private String transactionNo;
    private Timestamp transactionDate;
    private List<OrderItemResponse> items;
    private int totalItem;
    private long totalPrice;
    private long totalPaymentPrice;
    private String status;
    private Shipment shipment;
}
