package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.service.ShopUserService;
import com.ta.coremolde.shop.model.entity.Cart;
import com.ta.coremolde.shop.model.response.CartItemResponse;
import com.ta.coremolde.shop.model.response.CartResponse;
import com.ta.coremolde.shop.repository.CartRepository;
import com.ta.coremolde.shop.service.CartItemService;
import com.ta.coremolde.shop.service.CartService;
import com.ta.coremolde.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public CartResponse getCart(String email) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        Cart cart = cartRepository.findCartByShopUserId(shopUser.getId());
        List items = ResponseMapper.mapAsList(cartItemService.getAllItems(cart.getId()), CartItemResponse.class);

        return CartResponse.builder()
                .items(items)
                .totalItem(items.size())
                .totalPrice(calculateTotalPrice(items))
                .build();
    }

    @Override
    public CartItemResponse addToCart(String email, Integer productId, int qty) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        Cart cart;

        if (cartRepository.existsByShopUserId(shopUser.getId())) {
            cart = cartRepository.findCartByShopUserId(shopUser.getId());
        } else {
            cart = createCart(email);
        }

        return ResponseMapper.map(cartItemService.createItem(cart, productId, qty), CartItemResponse.class);
    }

    @Override
    public CartItemResponse updateItem(Integer cartItemId, int qty) {
        return ResponseMapper.map(cartItemService.updateItem(cartItemId, qty), CartItemResponse.class);
    }

    @Override
    public String removeFromCart(Integer cartItemId) {
        return cartItemService.deleteItem(cartItemId);
    }

    private Cart createCart(String email) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        Cart cart = Cart.builder()
                .shopUserId(shopUser.getId())
                .build();

        return cartRepository.save(cart);
    }

    private long calculateTotalPrice(List<CartItemResponse> items) {
        long total = 0;

        for (CartItemResponse item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

}
