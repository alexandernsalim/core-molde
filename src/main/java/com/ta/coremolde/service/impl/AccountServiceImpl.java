package com.ta.coremolde.service.impl;

import com.ta.coremolde.model.entity.Account;
import com.ta.coremolde.model.entity.Role;
import com.ta.coremolde.model.request.AccountRequest;
import com.ta.coremolde.model.response.AccountResponse;
import com.ta.coremolde.repository.AccountRepository;
import com.ta.coremolde.service.AccountService;
import com.ta.coremolde.service.RoleService;
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
        return accountRepository.findAccountByEmail(email);
    }

    @Override
    public AccountResponse register(AccountRequest accountRequest, String roleName) {
        Role role = roleService.getRole(roleName);

        Account account = Account.builder()
                .email(accountRequest.getEmail())
                .password(encoder.encode(accountRequest.getPassword()))
                .firstName(accountRequest.getFirstName())
                .lastName(accountRequest.getLastName())
                .phoneNo(accountRequest.getPhoneNo())
                .roleId(role)
                .build();

        return ResponseMapper.map(accountRepository.save(account), AccountResponse.class);
    }

}
