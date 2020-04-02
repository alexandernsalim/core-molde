package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.model.constant.PathConstant;
import com.ta.coremolde.master.model.request.ShopUserRequest;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.model.response.ShopUserResponse;
import com.ta.coremolde.master.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(PathConstant.SHOP_USER_MAPPING)
public class ShopUserController extends GlobalController {

    @Autowired
    private ShopUserService shopUserService;

    @PostMapping(PathConstant.REGISTER_SHOP_USER)
    public Response<ShopUserResponse> register(@ModelAttribute ShopUserRequest shopUserRequest, HttpServletRequest httpServletRequest) {
        Integer shopId = Integer.valueOf(httpServletRequest.getHeader("SHOP_ID"));

        return toResponse(shopUserService.register(shopUserRequest, shopId));
    }

}
