package com.ta.coremolde.shop.repository;

import com.ta.coremolde.shop.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findCartByShopUserId(Integer shopUserId);
    boolean existsByShopUserId(Integer shopUserId);

}
