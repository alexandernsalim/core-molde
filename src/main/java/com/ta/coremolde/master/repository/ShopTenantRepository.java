package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.ShopTenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopTenantRepository extends JpaRepository<ShopTenant, Integer> {
    ShopTenant findShopTenantById(Integer shopId);
}
