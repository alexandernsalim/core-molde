package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Customization;
import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.model.entity.ShopTenant;
import com.ta.coremolde.master.model.request.DeactivateShopRequest;
import com.ta.coremolde.master.model.request.UpdateShopRequest;
import com.ta.coremolde.master.repository.ShopRepository;
import com.ta.coremolde.master.service.ShopService;
import com.ta.coremolde.master.service.ShopTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    private static final String DB_PREFIX = "jdbc:postgresql://localhost:5432/";

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopTenantService shopTenantService;

    @Override
    public List<Shop> getShops() {
        return shopRepository.findAll();
    }

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
                .provinceId("0")
                .cityId("0")
                .build());
    }

    @Override
    public Shop updateShop(String email, UpdateShopRequest updateShopRequest) {
        Shop shop = shopRepository.findShopByAccount_Email(email);

        shop.setProvince(updateShopRequest.getProvince());
        shop.setProvinceId(updateShopRequest.getProvinceId());
        shop.setCity(updateShopRequest.getCity());
        shop.setCityId(updateShopRequest.getCityId());
        shop.setSubDistrict(updateShopRequest.getSubDistrict());
        shop.setStreet(updateShopRequest.getStreet());
        shop.setPostalCode(updateShopRequest.getPostalCode());

        return shopRepository.save(shop);
    }

    @Override
    public Shop activateShop(String email) {
        Shop shop = shopRepository.findShopByAccount_Email(email);
        shop.setIsActive(true);

        return shopRepository.save(shop);
    }

    @Override
    public Shop deactivateShop(String email, DeactivateShopRequest deactivateShopRequest) {
        Shop shop = shopRepository.findShopByAccount_Email(email);
        shop.setIsActive(false);
        shop.setDeactivateReason(deactivateShopRequest.getReason());

        return shopRepository.save(shop);
    }

    private String generateUrl(String shopName) {
        shopName = shopName.toLowerCase().replace(" ", "_");

        return DB_PREFIX + shopName;
    }

}
