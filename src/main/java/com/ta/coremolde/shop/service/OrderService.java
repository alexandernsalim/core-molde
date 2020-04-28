package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.CartItem;
import com.ta.coremolde.shop.model.entity.Order;
import com.ta.coremolde.shop.model.request.OrderRequest;

import java.util.List;

public interface OrderService {

    Order createOrder(Integer shopUserId, List<CartItem> items, OrderRequest orderRequest);
    Order acceptOrder();
    Order rejectOrder();

}
