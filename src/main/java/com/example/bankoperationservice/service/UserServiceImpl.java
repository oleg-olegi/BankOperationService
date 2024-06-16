package com.example.bankoperationservice.service;


import com.example.bankoperationservice.dto.RegisterDTO;
import com.example.bankoperationservice.dto.UserDTO;
import com.example.bankoperationservice.exceptions.UserIsAlreadyExistsException;
import com.example.bankoperationservice.exceptions.UserNotFoundException;
import com.example.bankoperationservice.mapper.ContactMapper;
import com.example.bankoperationservice.mapper.RegisterDtoMapper;
import com.example.bankoperationservice.mapper.UserMapper;
import com.example.bankoperationservice.model.BankAccount;
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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void createUser(RegisterDTO registerDTO) {
        logger.info("Trying to create and save to DB new user");
        if (!checkIfExistsUsername(registerDTO.getUserName())) {
            UserData userData = registerDtoMapper.INSTANCE.dtoToModel(registerDTO);
            logger.info("UserData from DTO {}", userData.toString());

            Contact contact = contactMapper.INSTANCE.dtoToContact(registerDTO);
            logger.info("ContactDTO mapped to Contact {}", contact);

            BankAccount bankAccount = bankAccountService.createBankAccount(userData);
            logger.info("Created bankAccount {}", bankAccount.toString());

            userData.setDateOfBirth(registerDTO.getDateOfBirth());
            userData.setInitialBalance(BigDecimal.valueOf(generatedRandomStartedBalance()));
            userData.setBankAccount(bankAccount);

            userData.setRole(registerDTO.getRole());

            bankAccount.setStartBalance(userData.getInitialBalance());
            bankAccount.setBalance(bankAccount.getStartBalance());
            bankAccount.setUserData(userData);

            contact.setPhones(contact.getPhones());
            contact.setEmail(contact.getEmail());
            contact.setUserData(userData);
            contactRepository.save(contact);
            logger.info("Контакт успешно сохранен в БД");

            logger.info("UserData {}", userData);
            logger.info("Bank account {}", bankAccount);
            logger.info("Contact {}", contact);
            userRepository.save(userData);
        } else {
            throw new UserIsAlreadyExistsException("UserName is busy");
        }
    }

    @Override
    public UserDTO getUserInfo(Long id, String username) {
        UserData foundedUser = checkIdUsernameAndAuthRules(id, username);
        return userMapper.INSTANCE.userModelToDTO(foundedUser);
    }

    @Override
    @Transactional
    public void updateUser(Long id, UserDTO updateUserDTO) {
        logger.info("Trying to update User");
        if (userRepository.findById(id).isPresent()) {
            UserData currentUserData = userRepository.findById(id).orElseThrow(
                    () -> new UserNotFoundException(
                            String.format("User with id '%s' not found", id))
            );

            logger.info("Data for updating {}", updateUserDTO.toString());
            if (updateUserDTO.getName() != null) {
                currentUserData.setName(updateUserDTO.getName());
            }
            if (updateUserDTO.getSurname() != null) {
                currentUserData.setSurname(updateUserDTO.getSurname());
            }
            if (updateUserDTO.getDateOfBirth() != null) {
                currentUserData.setDateOfBirth(updateUserDTO.getDateOfBirth());
            }
            userRepository.save(currentUserData);
            logger.info("UserData is successfully updated");
        }
    }


    @Override
    @Transactional
    public void deleteUser(Long id, String username) {
        checkIdUsernameAndAuthRules(id, username);
        contactRepository.deleteContactsByUserId(id);
        userRepository.deleteById(id);
    }

    private UserData checkIdUsernameAndAuthRules(Long id, String username) {
        UserData foundedUser = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id '%s' not found", id)));
        UserData userByUsername = userRepository.findByUserName(username).orElseThrow(() ->
                new UserNotFoundException(String.format("User with username '%s' not found", username)));
        if (!id.equals(userByUsername.getId())) {
            throw new AuthorizationServiceException("You are not authorized to view this user's information.");
        }
        return foundedUser;
    }


    @Override
    public boolean checkUserExists(String login) {
        return userRepository.existsByLogin(login);
    }

    private boolean checkIfExistsUsername(String username) {
        return userRepository.existsByLogin(username);
    }

    private double generatedRandomStartedBalance() {
        double randomValue = Math.random();
        return randomValue * (1000 - 1) + 1;
    }
}

