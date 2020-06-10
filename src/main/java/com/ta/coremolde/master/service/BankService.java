package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.entity.Bank;

import java.util.List;

public interface BankService {

    List<Bank> getAllBank();

    Bank getBank(Integer bankId);

    Bank addNewBank(String name);

    Bank editBank(Integer bankId, String name);

    String removeBank(Integer bankId);


}
