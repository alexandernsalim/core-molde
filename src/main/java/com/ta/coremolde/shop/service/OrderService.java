package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.CartItem;
import com.ta.coremolde.shop.model.entity.Order;
import com.ta.coremolde.shop.model.request.OrderRequest;
import com.ta.coremolde.shop.model.response.OrderResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OrderService {

    List<Order> getShopOrder();

    List<OrderResponse> getUserOrder(String email);

    OrderResponse getOrderDetail(Integer orderId);

    Order createOrder(Integer shopUserId, List<CartItem> items, OrderRequest orderRequest);

    Order acceptOrder(Integer orderId);

    Order cancelOrder(Integer orderId);

    Order uploadPaymentImage(Integer shopId, Integer orderId, MultipartFile paymentImage);

}
