package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.OrderItem;
import com.ta.coremolde.shop.model.response.CartItemResponse;

public interface OrderItemService {

    OrderItem createOrderItem(CartItemResponse item);

}
