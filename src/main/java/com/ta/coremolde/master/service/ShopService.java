package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.entity.Shop;

public interface ShopService {
    Shop getShopByAccountEmail(String email);
}
