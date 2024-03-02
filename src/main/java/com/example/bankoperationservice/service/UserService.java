package com.example.bankoperationservice.service;


import com.example.bankoperationservice.model.BankAccount;
import com.example.bankoperationservice.model.Contact;
import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.repository.IBankAccountRepository;
import com.example.bankoperationservice.repository.IContactRepository;
import com.example.bankoperationservice.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor
public class UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);
    private final IUserRepository userRepository;

    public void register(UserData user) {
        Contact contact = new Contact();
        BankAccount bankAccount = new BankAccount();

        user.setBankAccount(bankAccount);
        user.setContacts(new HashSet<>());
        contact.setUserData(user);

        contact.setEmail(user.getEmail());
        contact.setPhones(user.getPhone());
        user.getContacts().add(contact);

        bankAccount.setAccountNumber(generatePrefixedRandomAccountNumber());
        bankAccount.setBalance(user.getInitialBalance());
        bankAccount.setStartBalance(user.getInitialBalance());

        userRepository.save(user);
    }

    private String generatePrefixedRandomAccountNumber() {
        String prefix = "ACC";
        Random random = new Random();
        int number = 100000 + random.nextInt(900000);
        return prefix + number;
    }
}

