package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.master.model.exception.MoldeException;
import com.ta.coremolde.master.model.response.ErrorResponse;
import com.ta.coremolde.shop.model.entity.Cart;
import com.ta.coremolde.shop.model.entity.CartItem;
import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.repository.CartItemRepository;
import com.ta.coremolde.shop.service.CartItemService;
import com.ta.coremolde.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<CartItem> getAllItems(Integer cartId) {
        return cartItemRepository.findAllByCart_Id(cartId);
    }

    @Override
    public CartItem createItem(Cart cart, Integer productId, int qty) {
        CartItem item = cartItemRepository.findCartItemByCart_IdAndProduct_Id(cart.getId(), productId);
        Product product = productService.getProduct(productId);

        if (item != null) {
            int newQty = item.getQty() + qty;
            float totalWeight = newQty * product.getWeight();
            long totalPrice = newQty * product.getPrice();

            checkStock(newQty, product);
            item.setQty(newQty);
            item.setTotalWeight(totalWeight);
            item.setTotalPrice(totalPrice);

            return cartItemRepository.save(item);
        } else {
            checkStock(qty, product);

            float totalWeight = product.getWeight() * qty;
            long totalPrice = product.getPrice() * qty;

            return cartItemRepository.save(CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .totalWeight(totalWeight)
                    .totalPrice(totalPrice)
                    .qty(qty)
                    .build());
        }
    }

    @Override
    public CartItem updateItem(Integer cartItemId, int qty) {
        CartItem item = cartItemRepository.findCartItemById(cartItemId);

        if (item == null) {
            throw new MoldeException(
                    ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                    ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }

        Product product = item.getProduct();
        float totalWeight = qty * product.getWeight();
        long totalPrice = qty * product.getPrice();

        checkStock(qty, product);
        item.setQty(qty);
        item.setTotalWeight(totalWeight);
        item.setTotalPrice(totalPrice);

        return cartItemRepository.save(item);
    }

    @Override
    public String deleteItem(Integer cartItemId) {
        checkExistence(cartItemId);
        cartItemRepository.deleteById(cartItemId);

        return "Item removed successfully";
    }

    private void checkStock(int qty, Product product) {
        if (qty > product.getStock()) {
            throw new MoldeException(
                    ErrorResponse.STOCK_INSUFFICIENT.getCode(),
                    ErrorResponse.STOCK_INSUFFICIENT.getMessage()
            );
        }
    }

    private void checkExistence(Integer cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new MoldeException(
                    ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                    ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }
    }

}
