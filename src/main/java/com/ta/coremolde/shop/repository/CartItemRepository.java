package com.ta.coremolde.shop.repository;

import com.ta.coremolde.shop.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findAllByCart_Id(Integer cartId);

    CartItem findCartItemById(Integer id);

    CartItem findCartItemByCart_IdAndProduct_Id(Integer cartId, Integer productId);

    void deleteAllByCart_Id(Integer cartId);

}
