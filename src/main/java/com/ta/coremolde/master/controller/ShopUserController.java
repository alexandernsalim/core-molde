package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.model.constant.PathConstant;
import com.ta.coremolde.master.model.request.ChangePasswordRequest;
import com.ta.coremolde.master.model.request.EditShopUserRequest;
import com.ta.coremolde.master.model.request.ShopUserRequest;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.model.response.ShopUserResponse;
import com.ta.coremolde.master.service.ShopUserService;
import com.ta.coremolde.shop.model.response.DiscussionServiceResponse;
import com.ta.coremolde.shop.model.response.ReviewResponse;
import com.ta.coremolde.shop.service.DiscussionService;
import com.ta.coremolde.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(PathConstant.SHOP_USER_MAPPING)
public class ShopUserController extends GlobalController {

    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private DiscussionService discussionService;

    @GetMapping("/get")
    public Response<ShopUserResponse> getShopUser(HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(shopUserService.getShopUserInfo(email));
    }

    @GetMapping("/reviews")
    public Response<List<ReviewResponse>> getAllUserReviews(HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(reviewService.getUserReviews(email));
    }

    @GetMapping("/discussions")
    public Response<List<DiscussionServiceResponse>> getAllUserDiscussions(HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(discussionService.getUserDiscussions(email));
    }

    @PostMapping(PathConstant.REGISTER_SHOP_USER)
    public Response<ShopUserResponse> register(@ModelAttribute ShopUserRequest shopUserRequest, HttpServletRequest httpServletRequest) {
        Integer shopId = Integer.valueOf(httpServletRequest.getHeader("SHOP_ID"));

        return toResponse(shopUserService.register(shopUserRequest, shopId));
    }

    @PutMapping("/edit")
    public Response<ShopUserResponse> editShopUser(@ModelAttribute EditShopUserRequest editShopUserRequest, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(shopUserService.editShopUser(email, editShopUserRequest));
    }

    @PutMapping("/change-password")
    public Response<String> changePassword(@ModelAttribute ChangePasswordRequest changePasswordRequest, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(shopUserService.changePassword(email, changePasswordRequest));
    }

}
