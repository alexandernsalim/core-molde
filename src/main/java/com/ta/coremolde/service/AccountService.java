package com.ta.coremolde.service;

import com.ta.coremolde.model.entity.Account;
import com.ta.coremolde.model.request.AccountRequest;
import com.ta.coremolde.model.response.AccountResponse;

public interface AccountService {

    Account getAccount(String email);
    AccountResponse register(AccountRequest accountRequest, String roleName);

}
