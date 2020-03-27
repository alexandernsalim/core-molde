package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.repository.ShopRepository;
import com.ta.coremolde.master.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Shop getShopByAccountEmail(String email) {
        return shopRepository.findShopByAccount_Email(email);
    }

}
