package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.model.request.DeactivateShopRequest;
import com.ta.coremolde.master.model.request.UpdateShopRequest;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/molde/api/v1/shop")
public class ShopController extends GlobalController {

    @Autowired
    private ShopService shopService;

    @GetMapping
    public Response<List<Shop>> getShops() {
        return toResponse(shopService.getShops());
    }

    @GetMapping("/get")
    public Response<Shop> getShop(HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(shopService.getShopByAccountEmail(email));
    }

    @PutMapping("/update")
    public Response<Shop> updateShop(@ModelAttribute UpdateShopRequest updateShopRequest, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(shopService.updateShop(email, updateShopRequest));
    }

    @PutMapping("/activate")
    public Response<Shop> activateShop(HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(shopService.activateShop(email));
    }

    @PutMapping("/deactivate")
    public Response<Shop> deactivateShop(@ModelAttribute DeactivateShopRequest deactivateShopRequest, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(shopService.deactivateShop(email, deactivateShopRequest));
    }

}
