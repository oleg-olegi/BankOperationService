package com.example.bankoperationservice.service;

import com.example.bankoperationservice.model.BankAccount;
import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.service.interfaces.BankAccountService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BankAccountImpl implements BankAccountService {
    @Override
    public BankAccount createBankAccount(UserData userData) {
        BankAccount bankAccount = new BankAccount();

        bankAccount.setStartBalance(userData.getInitialBalance());
        bankAccount.setBalance(bankAccount.getStartBalance());
        bankAccount.setAccountNumber(generatePrefixedRandomAccountNumber());
        bankAccount.setUserData(userData);

        return bankAccount;
    }

    private String generatePrefixedRandomAccountNumber() {
        String prefix = "ACC";
        Random random = new Random();
        int number = 100000 + random.nextInt(900000);
        return prefix + number;
    }
}
