package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.request.BankAccountRequest;
import com.ta.coremolde.master.model.response.BankAccountResponse;

import java.util.List;

public interface BankAccountService {

    List<BankAccountResponse> getShopBankAccount(String email, Integer shopId);

    BankAccountResponse getBankAccount(Integer bankAccountId);

    BankAccountResponse addBankAccount(String email, Integer bankId, BankAccountRequest request);

    BankAccountResponse editBankAccount(Integer bankAccountId, BankAccountRequest request);

    String removeBankAccount(Integer bankAccountId);


}
