package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.service.ShopUserService;
import com.ta.coremolde.shop.model.entity.Cart;
import com.ta.coremolde.shop.model.entity.CartItem;
import com.ta.coremolde.shop.model.entity.Order;
import com.ta.coremolde.shop.model.request.OrderRequest;
import com.ta.coremolde.shop.model.response.CartItemResponse;
import com.ta.coremolde.shop.model.response.CartResponse;
import com.ta.coremolde.shop.model.response.OrderItemResponse;
import com.ta.coremolde.shop.model.response.OrderResponse;
import com.ta.coremolde.shop.repository.CartRepository;
import com.ta.coremolde.shop.service.CartItemService;
import com.ta.coremolde.shop.service.CartService;
import com.ta.coremolde.shop.service.OrderService;
import com.ta.coremolde.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;

    @Override
    public CartResponse getCart(String email) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        Cart cart;

        if (cartRepository.existsByShopUserId(shopUser.getId())) {
            cart = cartRepository.findCartByShopUserId(shopUser.getId());
        } else {
            cart = createCart(shopUser.getId());
        }

        List items = ResponseMapper.mapAsList(cartItemService.getAllItems(cart.getId()), CartItemResponse.class);

        return CartResponse.builder()
                .cartId(cart.getId())
                .items(items)
                .totalItem(calculateTotalItem(items))
                .totalPrice(calculateTotalPrice(items))
                .totalWeight(calculateTotalWeight(items))
                .build();
    }

    @Override
    public CartItemResponse addToCart(String email, Integer productId, int qty) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        Cart cart;

        if (cartRepository.existsByShopUserId(shopUser.getId())) {
            cart = cartRepository.findCartByShopUserId(shopUser.getId());
        } else {
            cart = createCart(shopUser.getId());
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

    @Override
    public OrderResponse checkout(String email, OrderRequest orderRequest) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        Cart cart = cartRepository.findCartByShopUserId(shopUser.getId());
        List<CartItem> items = cartItemService.getAllItems(cart.getId());
        Order order = orderService.createOrder(shopUser.getId(), items, orderRequest);
        cartItemService.removeAllItem(cart.getId());

        return OrderResponse.builder()
                .id(order.getId())
                .transactionNo(order.getTransactionNo())
                .transactionDate(order.getTransactionDate())
                .items(ResponseMapper.mapAsList(items, OrderItemResponse.class))
                .totalItem(calculateTotalItem(ResponseMapper.mapAsList(items, CartItemResponse.class)))
                .totalPrice(order.getTotalPrice())
                .totalPaymentPrice(order.getTotalPaymentPrice())
                .status(order.getStatus())
                .shipment(order.getShipment())
                .build();
    }

    private Cart createCart(Integer shopUserId) {
        return cartRepository.save(Cart.builder()
                .shopUserId(shopUserId)
                .build());
    }

    private int calculateTotalItem(List<CartItemResponse> items) {
        int total = 0;

        for (CartItemResponse item : items) {
            total += item.getQty();
        }

        return total;
    }

    private long calculateTotalPrice(List<CartItemResponse> items) {
        long total = 0;

        for (CartItemResponse item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    private int calculateTotalWeight(List<CartItemResponse> items) {
        float total = 0;

        for (CartItemResponse item : items) {
            total += item.getTotalWeight();
        }

        total *= 1000;

        return (int) total;
    }

}
