package com.example.bankoperationservice.service;

import com.example.bankoperationservice.model.BankAccount;
import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.repository.IBankAccountRepository;
import com.example.bankoperationservice.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {


    @Mock
    private IUserRepository userRepository;

    @Mock
    private IBankAccountRepository bankRepository;

    @InjectMocks
    private TransferService transferService;


    @BeforeEach
    void setUp() {
        UserData sender = new UserData();
        sender.setId(1L);
        BankAccount senderBankAccount = new BankAccount();
        senderBankAccount.setBalance(BigDecimal.valueOf(1000));
        sender.setBankAccount(senderBankAccount);

        UserData recipient = new UserData();
        recipient.setId(2L);
        BankAccount recipientBankAccount = new BankAccount();
        recipientBankAccount.setBalance(BigDecimal.valueOf(500));
        recipient.setBankAccount(recipientBankAccount);
    }

    @Test
    void transferMoney_SenderNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, // Здесь ожидается NoSuchElementException, так как findById возвращает Optional
                () -> transferService.transferMoney(1L, 2L, BigDecimal.valueOf(500)));
        verify(userRepository, never()).save(any());
    }
}
