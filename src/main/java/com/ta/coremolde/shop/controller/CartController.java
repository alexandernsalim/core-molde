package com.ta.coremolde.shop.controller;

import com.ta.coremolde.master.controller.GlobalController;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.shop.model.response.CartItemResponse;
import com.ta.coremolde.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("molde/api/v1/cart")
public class CartController extends GlobalController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{productId}/{qty}/add")
    public Response<CartItemResponse> addItemToCart(@PathVariable Integer productId, @PathVariable int qty, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(cartService.addToCart(email, productId, qty));
    }

    @PutMapping("{cartItemId}/{qty}/update")
    public Response<CartItemResponse> updateItem(@PathVariable Integer cartItemId, @PathVariable int qty) {
        //TODO Secure from other user
        return toResponse(cartService.updateItem(cartItemId, qty));
    }

    @DeleteMapping("/{cartItemId}/remove")
    public Response<String> removeItemFromCart(@PathVariable Integer cartItemId) {
        //TODO Secure from other user
        return toResponse(cartService.removeFromCart(cartItemId));
    }

}