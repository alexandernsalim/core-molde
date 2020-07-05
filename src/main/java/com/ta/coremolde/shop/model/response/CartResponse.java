package com.ta.coremolde.shop.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private Integer cartId;
    private List<CartItemResponse> items;
    private int totalItem;
    private int totalWeight;
    private long totalPrice;

}
