package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Role;
import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.service.AccountService;
import com.ta.coremolde.master.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ShopUserService shopUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountService.getAccount(email);
        ShopUser shopUser = shopUserService.getShopUser(email);

        if (account == null && shopUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String password = (account == null) ? shopUser.getPassword() : account.getPassword();
        List<Role> roles = new ArrayList<>();
        roles.add((account == null) ? shopUser.getRole() : account.getRole());

        return new User(
                email,
                password,
                getAuthorities(roles)
        );
    }

    private List<GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

}
