package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.entity.Bank;
import com.ta.coremolde.master.repository.BankRepository;
import com.ta.coremolde.master.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public List<Bank> getAllBank() {
        return bankRepository.findAll();
    }

    @Override
    public Bank getBank(Integer bankId) {
        return bankRepository.getOne(bankId);
    }

    @Override
    public Bank addNewBank(String name) {
        return bankRepository.save(Bank.builder()
                .name(name).build());
    }

    @Override
    public Bank editBank(Integer bankId, String name) {
        Bank bank = bankRepository.getOne(bankId);
        bank.setName(name);

        return bankRepository.save(bank);
    }

    @Override
    public String removeBank(Integer bankId) {
        bankRepository.deleteById(bankId);

        return "Bank successfully deleted";
    }


}
