package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.request.OrderRequest;
import com.ta.coremolde.shop.model.response.CartItemResponse;
import com.ta.coremolde.shop.model.response.CartResponse;
import com.ta.coremolde.shop.model.response.OrderResponse;

public interface CartService {

    CartResponse getCart(String email);

    CartItemResponse addToCart(String email, Integer productId, int qty);

    CartItemResponse updateItem(Integer cartItemId, int qty);

    String removeFromCart(Integer cartItemId);

    OrderResponse checkout(String email, OrderRequest orderRequest);

}
