package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByEmail(String email);

}
