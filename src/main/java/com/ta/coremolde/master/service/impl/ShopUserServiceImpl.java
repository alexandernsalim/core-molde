package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.constant.RoleConstant;
import com.ta.coremolde.master.model.entity.Role;
import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.model.exception.MoldeException;
import com.ta.coremolde.master.model.request.ChangePasswordRequest;
import com.ta.coremolde.master.model.request.EditShopUserRequest;
import com.ta.coremolde.master.model.request.ShopUserRequest;
import com.ta.coremolde.master.model.response.ErrorResponse;
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
    public ShopUser getShopUserById(Integer shopUserId) {
        return shopUserRepository.findShopUserById(shopUserId);
    }

    @Override
    public ShopUserResponse getShopUserInfo(String email) {
        return ResponseMapper.map(shopUserRepository.findShopUserByEmail(email), ShopUserResponse.class);
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

    @Override
    public ShopUserResponse editShopUser(String email, EditShopUserRequest editShopUserRequest) {
        ShopUser shopUser = shopUserRepository.findShopUserByEmail(email);
        shopUser.setEmail(editShopUserRequest.getEmail());
        shopUser.setFirstName(editShopUserRequest.getFirstName());
        shopUser.setLastName(editShopUserRequest.getLastName());
        shopUser.setPhoneNo(editShopUserRequest.getPhoneNo());

        return ResponseMapper.map(shopUserRepository.save(shopUser), ShopUserResponse.class);
    }

    @Override
    public String changePassword(String email, ChangePasswordRequest changePasswordRequest) {
        ShopUser shopUser = shopUserRepository.findShopUserByEmail(email);
        if (encoder.matches(changePasswordRequest.getOldPassword(), shopUser.getPassword())) {
            shopUser.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
            shopUserRepository.save(shopUser);

            return "Password changed";
        } else {
            throw new MoldeException(
                    ErrorResponse.PASSWORD_NOT_MATCH.getCode(),
                    ErrorResponse.PASSWORD_NOT_MATCH.getMessage()
            );
        }
    }

}
