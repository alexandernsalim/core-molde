package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Customization;
import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.model.entity.ShopTenant;
import com.ta.coremolde.master.repository.ShopRepository;
import com.ta.coremolde.master.service.ShopService;
import com.ta.coremolde.master.service.ShopTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopServiceImpl implements ShopService {
    private static final String DB_PREFIX = "jdbc:postgresql://localhost:5432/";

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopTenantService shopTenantService;

    @Override
    public Shop getShop(Integer id) {
        return shopRepository.findShopById(id);
    }

    @Override
    public Shop getShopByAccountEmail(String email) {
        return shopRepository.findShopByAccount_Email(email);
    }


    @Override
    @Transactional("masterTransactionManager")
    public Shop createShop(String name, Account account, Customization customization) {
        ShopTenant shopTenant = shopTenantService.createShopTenant(generateUrl(name), "molde_admin", "molde_admin");

        return shopRepository.save(Shop.builder()
                .name(name)
                .province("-")
                .city("-")
                .subDistrict("-")
                .street("-")
                .postalCode("-")
                .account(account)
                .shopTenant(shopTenant)
                .customization(customization)
                .build());
    }

    private String generateUrl(String shopName) {
        shopName = shopName.toLowerCase().replace(" ", "_");

        return DB_PREFIX + shopName;
    }

}
