package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.BankAccount;
import com.ta.coremolde.master.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    List<BankAccount> findAllByShopEquals(Shop shop);
    List<BankAccount> findAllByShop_IdEquals(Integer shopId);

}
