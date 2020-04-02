package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.constant.RoleConstant;
import com.ta.coremolde.master.model.entity.Role;
import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.model.request.ShopUserRequest;
import com.ta.coremolde.master.model.response.ShopUserResponse;
import com.ta.coremolde.master.repository.ShopUserRepository;
import com.ta.coremolde.master.service.RoleService;
import com.ta.coremolde.master.service.ShopService;
import com.ta.coremolde.master.service.ShopUserService;
import com.ta.coremolde.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ShopUserServiceImpl implements ShopUserService {

    @Autowired
    private ShopUserRepository shopUserRepository;

    @Lazy
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ShopService shopService;

    @Override
    public ShopUser getShopUser(String email) {
        return shopUserRepository.findShopUserByEmail(email);
    }

    @Override
    public ShopUserResponse register(ShopUserRequest shopUserRequest, Integer shopId) {
        Role role = roleService.getRole(RoleConstant.SHOP_USER);
        Shop shop = shopService.getShop(shopId);
        ShopUser shopUser = ShopUser.builder()
                .email(shopUserRequest.getEmail())
                .password(encoder.encode(shopUserRequest.getPassword()))
                .firstName(shopUserRequest.getFirstName())
                .lastName(shopUserRequest.getLastName())
                .phoneNo(shopUserRequest.getPhoneNo())
                .role(role)
                .shop(shop)
                .build();

        return ResponseMapper.map(shopUserRepository.save(shopUser), ShopUserResponse.class);
    }

}
