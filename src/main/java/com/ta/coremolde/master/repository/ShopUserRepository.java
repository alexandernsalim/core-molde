package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopUserRepository extends JpaRepository<ShopUser, Integer> {

    ShopUser findShopUserByEmail(String email);
    ShopUser findShopUserById(Integer shopUserId);
    List<ShopUser> findAllByShopId(Integer shopId);

}
