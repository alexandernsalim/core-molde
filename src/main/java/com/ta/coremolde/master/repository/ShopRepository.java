package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    List<Shop> findAllByIsActive(Boolean status);
    Shop findShopById(Integer id);
    Shop findShopByAccount_Email(String email);

}
