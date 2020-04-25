package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.CartItem;
import com.ta.coremolde.shop.model.entity.Order;
import com.ta.coremolde.shop.model.entity.OrderItem;
import com.ta.coremolde.shop.model.response.CartItemResponse;

import java.util.List;

public interface OrderItemService {

    void createOrderItem(Order order, List<CartItem> items);

}
