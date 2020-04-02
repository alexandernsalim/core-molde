package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Customization;
import com.ta.coremolde.master.model.entity.Shop;

public interface ShopService {
    Shop getShop(Integer id);
    Shop getShopByAccountEmail(String email);
    Shop createShop(String name, Account account, Customization customization);
}
