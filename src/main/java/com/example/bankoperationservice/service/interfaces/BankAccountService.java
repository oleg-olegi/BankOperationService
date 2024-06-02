package com.example.bankoperationservice.service.interfaces;

import com.example.bankoperationservice.model.BankAccount;
import com.example.bankoperationservice.model.UserData;

public interface BankAccountService {
    BankAccount createBankAccount(UserData userData);
}
