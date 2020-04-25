package com.ta.coremolde.shop.model.response;

import com.ta.coremolde.shop.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {
    private Integer id;
    private Product product;
    private int qty;
    private float totalWeight;
    private long totalPrice;
}
