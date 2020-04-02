package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Role;
import com.ta.coremolde.master.model.request.AccountRequest;
import com.ta.coremolde.master.model.response.AccountResponse;
import com.ta.coremolde.master.repository.AccountRepository;
import com.ta.coremolde.master.service.AccountService;
import com.ta.coremolde.master.service.RoleService;
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

    @Override
    public Account getAccount(String email) {
        //TODO Add error handling
        return accountRepository.findAccountByEmail(email);
    }

    @Override
    public AccountResponse register(AccountRequest accountRequest, String roleName) {
        //TODO Add error handling
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

}