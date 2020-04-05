package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.Cart;
import com.ta.coremolde.shop.model.entity.CartItem;

import java.util.List;

public interface CartItemService {

    List<CartItem> getAllItems(Integer cartId);
    CartItem createItem(Cart cart, Integer productId, int qty);
    CartItem updateItem(Integer cartItemId, int qty);
    String deleteItem(Integer cartItemId);

}
