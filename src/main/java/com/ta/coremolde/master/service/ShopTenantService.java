package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.entity.ShopTenant;

public interface ShopTenantService {

    ShopTenant createShopTenant(String url, String username, String password);

}
