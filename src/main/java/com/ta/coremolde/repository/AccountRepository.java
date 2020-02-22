package com.ta.coremolde.repository;

import com.ta.coremolde.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByEmail(String email);

}
