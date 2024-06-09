package com.example.bankoperationservice.repository;

import com.example.bankoperationservice.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBankAccountRepository extends JpaRepository<BankAccount, String> {
    @Query("SELECT acc FROM BankAccount acc WHERE acc.balance<(acc.startBalance*2.07)")
    List<BankAccount> balanceLessThenLimit();

    @Modifying
    @Query("DELETE FROM BankAccount b WHERE b.userData.id = :userId")
    void deleteBankAccountByUserId(@Param("userId") Long userId);
}
