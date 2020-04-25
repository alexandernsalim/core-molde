package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.entity.Bank;
import com.ta.coremolde.master.model.entity.BankAccount;
import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.model.request.BankAccountRequest;
import com.ta.coremolde.master.model.response.BankAccountResponse;
import com.ta.coremolde.master.repository.BankAccountRepository;
import com.ta.coremolde.master.service.BankAccountService;
import com.ta.coremolde.master.service.BankService;
import com.ta.coremolde.master.service.ShopService;
import com.ta.coremolde.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankService bankService;

    @Autowired
    private ShopService shopService;

    @Override
    public List<BankAccountResponse> getShopBankAccount(String email) {
        Shop shop = shopService.getShopByAccountEmail(email);

        return ResponseMapper.mapAsList(bankAccountRepository.findAllByShopEquals(shop), BankAccountResponse.class);
    }

    @Override
    public BankAccountResponse getBankAccount(Integer bankAccountId) {
        return ResponseMapper.map(bankAccountRepository.getOne(bankAccountId), BankAccountResponse.class);
    }

    @Override
    public BankAccountResponse addBankAccount(String email, Integer bankId, BankAccountRequest request) {
        Shop shop = shopService.getShopByAccountEmail(email);
        Bank bank = bankService.getBank(bankId);

        BankAccount bankAccount = BankAccount.builder()
                .no(request.getNo())
                .owner(request.getOwner())
                .bank(bank)
                .shop(shop).build();

        return ResponseMapper.map(bankAccountRepository.save(bankAccount), BankAccountResponse.class);
    }

    @Override
    public BankAccountResponse editBankAccount(Integer bankAccountId, BankAccountRequest request) {
        BankAccount bankAccount = bankAccountRepository.getOne(bankAccountId);
        bankAccount.setNo(request.getNo());
        bankAccount.setOwner(request.getOwner());

        return ResponseMapper.map(bankAccountRepository.save(bankAccount), BankAccountResponse.class);
    }

    @Override
    public String removeBankAccount(Integer bankAccountId) {
        bankAccountRepository.deleteById(bankAccountId);

        return "Bank account successfully deleted";
    }
}
