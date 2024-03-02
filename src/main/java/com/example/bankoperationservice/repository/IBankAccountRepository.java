package com.example.bankoperationservice.repository;

import com.example.bankoperationservice.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Query ("SELECT acc FROM BankAccount acc WHERE acc.balance<(acc.startBalance*2.07)")
    List<BankAccount> balanceLessThenLimit();
}
