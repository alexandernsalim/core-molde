package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.model.constant.PathConstant;
import com.ta.coremolde.master.model.constant.RoleConstant;
import com.ta.coremolde.master.model.request.AccountRequest;
import com.ta.coremolde.master.model.response.AccountResponse;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(PathConstant.ACCOUNT_MAPPING)
public class AccountController extends GlobalController {

    @Autowired
    private AccountService accountService;

    @PostMapping(PathConstant.REGISTER_ADMIN)
    public Response<AccountResponse> registerAdmin(@ModelAttribute AccountRequest accountRequest) {
        return toResponse(accountService.register(accountRequest, RoleConstant.ADMIN));
    }

    @PostMapping(PathConstant.REGISTER_CLIENT)
    public Response<AccountResponse> registerClient(@ModelAttribute AccountRequest accountRequest) {
        return toResponse(accountService.register(accountRequest, RoleConstant.CLIENT));
    }

    @GetMapping("/has-shop")
    public Response<Boolean> hasShop(HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(accountService.hasShop(email));
    }

}
