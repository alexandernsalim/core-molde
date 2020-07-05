package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.model.request.ChangePasswordRequest;
import com.ta.coremolde.master.model.request.EditShopUserRequest;
import com.ta.coremolde.master.model.request.ShopUserRequest;
import com.ta.coremolde.master.model.response.ShopUserResponse;

import java.util.List;

public interface ShopUserService {
    ShopUser getShopUser(String email);

    List<ShopUserResponse> getShopCustomer(String email);

    ShopUser getShopUserById(Integer shopUserId);

    ShopUserResponse getShopUserInfo(String email);

    ShopUserResponse register(ShopUserRequest shopUserRequest, Integer shopId);

    ShopUserResponse editShopUser(String email, EditShopUserRequest editShopUserRequest);

    String changePassword(String email, ChangePasswordRequest changePasswordRequest);
}
