package com.example.bankoperationservice.service;


import com.example.bankoperationservice.dto.RegisterDTO;
import com.example.bankoperationservice.exceptions.UserIsAlreadyExistsException;
import com.example.bankoperationservice.mapper.ContactMapper;
import com.example.bankoperationservice.mapper.RegisterDtoMapper;
import com.example.bankoperationservice.model.Contact;
import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.repository.IBankAccountRepository;
import com.example.bankoperationservice.repository.IContactRepository;
import com.example.bankoperationservice.repository.IUserRepository;
import com.example.bankoperationservice.service.interfaces.BankAccountService;
import com.example.bankoperationservice.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final IUserRepository userRepository;
    private final IBankAccountRepository bankAccountRepository;
    private final IContactRepository contactRepository;
    private final RegisterDtoMapper registerDtoMapper;
    private final ContactMapper contactMapper;
    private final BankAccountService bankAccountService;

    @Override
    public void createUser(RegisterDTO registerDTO) {
        logger.info("Trying to create and save to DB new user");

        if (checkIfExistsUsername(registerDTO.getUserName())) {

            UserData userData = registerDtoMapper.INSTANCE.dtoToModel(registerDTO);
            Contact contact = contactMapper.INSTANCE.dtoToContact(registerDTO);
            contact.setUserData(userData);

            logger.info("DTO to Entity {}", userData.toString());

            userData.setDateOfBirth(registerDTO.getDateOfBirth());
            userData.setInitialBalance(BigDecimal.valueOf(generatedRandomStartedBalance()));

            userData.setBankAccount(bankAccountService.createBankAccount(userData));

            logger.info("UserData {}", userData);
            userRepository.save(userData);
            logger.info("Bank account {}", userData.getBankAccount().toString());
            bankAccountRepository.save(userData.getBankAccount());
            contactRepository.save(contact);
        } else {
            throw new UserIsAlreadyExistsException("UserName is busy");
        }
    }

    @Override
    public boolean checkUserForRegisterOk(String login) {
        return false;
    }

    @Override
    public boolean checkUserExists(String login) {
        return userRepository.existsByLogin(login);
    }

//    public void register(UserData user) {
//
//        Contact contact = new Contact();
//        BankAccount bankAccount = new BankAccount();
//
//        user.setBankAccount(bankAccount);
//        user.setContacts(new HashSet<>());
//        contact.setUserData(user);
//
//        contact.setEmail(user.getEmail());
//        contact.setPhones(user.getPhone());
//        user.getContacts().add(contact);
//
//        bankAccount.setAccountNumber(generatePrefixedRandomAccountNumber());
//        bankAccount.setBalance(user.getInitialBalance());
//        bankAccount.setStartBalance(user.getInitialBalance());
//
//        userRepository.save(user);
//    }

//    private BankAccount createBankAccount(UserData userData) {
//        BankAccount bankAccount = new BankAccount();
//
//        bankAccount.setStartBalance(userData.getInitialBalance());
//        bankAccount.setBalance(bankAccount.getStartBalance());
//        bankAccount.setAccountNumber(generatePrefixedRandomAccountNumber());
//        bankAccount.setUserData(userData);
//
//        return bankAccount;
//    }

    private boolean checkIfExistsUsername(String userName) {
        return userRepository.findByUserName(userName).isEmpty();
    }

    private double generatedRandomStartedBalance() {
        double randomValue = Math.random();
        return randomValue * (1000 - 1) + 1;
    }
}

