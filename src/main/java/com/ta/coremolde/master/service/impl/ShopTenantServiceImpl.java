package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.entity.ShopTenant;
import com.ta.coremolde.master.repository.ShopTenantRepository;
import com.ta.coremolde.master.service.ShopTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopTenantServiceImpl implements ShopTenantService {

    @Autowired
    private ShopTenantRepository shopTenantRepository;

    @Override
    public ShopTenant createShopTenant(String url, String username, String password) {
        return shopTenantRepository.save(ShopTenant.builder()
                .url(url)
                .username(username)
                .password(password).build());
    }

}
