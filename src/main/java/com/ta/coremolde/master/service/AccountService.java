package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.model.request.AccountRequest;
import com.ta.coremolde.master.model.response.AccountResponse;

public interface AccountService {

    Account getAccount(String email);

    AccountResponse register(AccountRequest accountRequest, String roleName);

}
