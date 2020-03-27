package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.repository.ShopUserRepository;
import com.ta.coremolde.master.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopUserServiceImpl implements ShopUserService {

    @Autowired
    private ShopUserRepository shopUserRepository;

    @Override
    public ShopUser getShopUser(String email) {
        return shopUserRepository.findShopUserByEmail(email);
    }

}
