package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Customization;
import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.model.request.DeactivateShopRequest;
import com.ta.coremolde.master.model.request.UpdateShopRequest;

import java.util.List;

public interface ShopService {
    List<Shop> getShops();

    List<Shop> getShopsByStatus(Boolean status);

    Shop getShop(Integer id);

    Shop getShopByAccountEmail(String email);

    Shop createShop(String name, Account account, Customization customization);

    Shop updateShop(String email, UpdateShopRequest updateShopRequest);

    Shop activateShop(Integer shopId);

    Shop deactivateShop(Integer shopId, DeactivateShopRequest deactivateShopRequest);
}
