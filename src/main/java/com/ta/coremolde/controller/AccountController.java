package com.ta.coremolde.controller;

import com.ta.coremolde.model.constant.PathConstant;
import com.ta.coremolde.model.constant.RoleConstant;
import com.ta.coremolde.model.request.AccountRequest;
import com.ta.coremolde.model.response.AccountResponse;
import com.ta.coremolde.model.response.Response;
import com.ta.coremolde.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
