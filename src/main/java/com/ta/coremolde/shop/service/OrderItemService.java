package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.CartItem;
import com.ta.coremolde.shop.model.entity.Order;
import com.ta.coremolde.shop.model.entity.OrderItem;
import com.ta.coremolde.shop.model.response.CartItemResponse;
import com.ta.coremolde.shop.model.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {

    List<OrderItemResponse> getOrderItems(Integer orderId);

    void createOrderItem(Order order, List<CartItem> items);

    void removeOrderItem(Integer orderItemId);

}
