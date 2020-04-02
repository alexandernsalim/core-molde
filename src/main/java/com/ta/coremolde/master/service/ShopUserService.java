package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.model.request.ShopUserRequest;
import com.ta.coremolde.master.model.response.ShopUserResponse;

public interface ShopUserService {

    ShopUser getShopUser(String email);
    ShopUserResponse register(ShopUserRequest shopUserRequest, Integer shopId);

}
