package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.model.request.AccountRequest;
import com.ta.coremolde.master.model.request.ChangePasswordRequest;
import com.ta.coremolde.master.model.request.UpdateAccountRequest;
import com.ta.coremolde.master.model.response.AccountResponse;

public interface AccountService {
    boolean hasShop(String email);

    Account getAccount(String email);

    AccountResponse register(AccountRequest accountRequest, String roleName);

    AccountResponse updateAccount(String email, UpdateAccountRequest updateAccountRequest);

    String changePassword(String email, ChangePasswordRequest changePasswordRequest);
}
