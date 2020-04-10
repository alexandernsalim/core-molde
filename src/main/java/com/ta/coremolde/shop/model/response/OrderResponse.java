package com.ta.coremolde.shop.model.response;

import com.ta.coremolde.shop.model.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Integer orderId;
    private String transactionNo;
    private List<OrderItem> items;
    private int totalItem;
    private long totalPrice;
    private long totalShipmentPrice;
    private long totalPaymentPrice;
    private String status;
}
