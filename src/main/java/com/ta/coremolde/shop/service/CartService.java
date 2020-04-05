package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.response.CartItemResponse;
import com.ta.coremolde.shop.model.response.CartResponse;

public interface CartService {

    CartResponse getCart(String email);

    CartItemResponse addToCart(String email, Integer productId, int qty);

    CartItemResponse updateItem(Integer cartItemId, int qty);

    String removeFromCart(Integer cartItemId);

}
