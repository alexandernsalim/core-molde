package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {
}
