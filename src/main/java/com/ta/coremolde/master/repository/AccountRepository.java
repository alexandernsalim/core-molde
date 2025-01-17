package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Role;
import com.ta.coremolde.master.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByEmail(String email);

    Account findAccountByEmail(String email);
}
