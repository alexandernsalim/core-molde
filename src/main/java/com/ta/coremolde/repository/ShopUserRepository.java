package com.ta.coremolde.repository;

import com.ta.coremolde.model.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopUserRepository extends JpaRepository<ShopUser, Integer> {

    ShopUser findShopUserByEmail(String email);

}