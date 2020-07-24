package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Role;
import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.model.exception.MoldeException;
import com.ta.coremolde.master.model.request.AccountRequest;
import com.ta.coremolde.master.model.request.ChangePasswordRequest;
import com.ta.coremolde.master.model.request.UpdateAccountRequest;
import com.ta.coremolde.master.model.response.AccountResponse;
import com.ta.coremolde.master.model.response.ErrorResponse;
import com.ta.coremolde.master.repository.AccountRepository;
import com.ta.coremolde.master.service.AccountService;
import com.ta.coremolde.master.service.RoleService;
import com.ta.coremolde.master.service.ShopService;
import com.ta.coremolde.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ShopService shopService;

    @Override
    public Account getAccount(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    @Override
    public AccountResponse register(AccountRequest accountRequest, String roleName) {
        if (accountRepository.existsByEmail(accountRequest.getEmail())) {
            throw new MoldeException(
                    ErrorResponse.EMAIL_EXISTS.getCode(),
                    ErrorResponse.EMAIL_EXISTS.getMessage()
            );
        }

        Role role = roleService.getRole(roleName);
        Account account = Account.builder()
                .email(accountRequest.getEmail())
                .password(encoder.encode(accountRequest.getPassword()))
                .firstName(accountRequest.getFirstName())
                .lastName(accountRequest.getLastName())
                .phoneNo(accountRequest.getPhoneNo())
                .role(role)
                .build();

        return ResponseMapper.map(accountRepository.save(account), AccountResponse.class);
    }

    @Override
    public AccountResponse updateAccount(String email, UpdateAccountRequest updateAccountRequest) {
        Account account = accountRepository.findAccountByEmail(email);
        account.setEmail(updateAccountRequest.getEmail());
        account.setFirstName(updateAccountRequest.getFirstName());
        account.setLastName(updateAccountRequest.getLastName());
        account.setPhoneNo(updateAccountRequest.getPhoneNo());

        return ResponseMapper.map(accountRepository.save(account), AccountResponse.class);
    }

    @Override
    public String changePassword(String email, ChangePasswordRequest changePasswordRequest) {
        Account account = accountRepository.findAccountByEmail(email);
        if (encoder.matches(changePasswordRequest.getOldPassword(), account.getPassword())) {
            account.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
            accountRepository.save(account);

            return "Password changed";
        } else {
            throw new MoldeException(
                    ErrorResponse.PASSWORD_NOT_MATCH.getCode(),
                    ErrorResponse.PASSWORD_NOT_MATCH.getMessage()
            );
        }
    }

    @Override
    public boolean hasShop(String email) {
        Shop shop = shopService.getShopByAccountEmail(email);
        return shop != null;
    }

}
