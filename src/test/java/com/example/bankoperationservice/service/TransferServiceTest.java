package com.example.bankoperationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import com.example.bankoperationservice.exceptions.InsufficientFundsException;
import com.example.bankoperationservice.model.BankAccount;
import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.repository.IBankAccountRepository;
import com.example.bankoperationservice.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IBankAccountRepository bankRepository;
    @InjectMocks
    private TransferService transferService;

    @BeforeEach
    public void setUp() {
        transferService = new TransferService(userRepository, bankRepository);
    }

    @Test
    public void testTransferMoneySufficientFunds() {
        Long senderId = 1L;
        Long recipientId = 2L;

        UserData sender = new UserData();
        UserData recipient = new UserData();

        sender.setId(senderId);
        recipient.setId(recipientId);

        BigDecimal senderBalance = BigDecimal.valueOf(200);
        BigDecimal recipientBalance = BigDecimal.valueOf(300);

        BankAccount senderBankAcc = new BankAccount();
        senderBankAcc.setBalance(senderBalance);
        BankAccount recipientBankAcc = new BankAccount();
        recipientBankAcc.setBalance(recipientBalance);

        sender.setBankAccount(senderBankAcc);
        recipient.setBankAccount(recipientBankAcc);

        when(userRepository.findById(senderId)).thenReturn(Optional.of(sender));
        when(userRepository.findById(recipientId)).thenReturn(Optional.of(recipient));

        BigDecimal amount = BigDecimal.valueOf(100);

        transferService.transferMoney(senderId, recipientId, amount);

        assertEquals(BigDecimal.valueOf(100), sender.getBankAccount().getBalance());
        assertEquals(BigDecimal.valueOf(400), recipient.getBankAccount().getBalance());
    }

    @Test
    public void testTransferMoneyInsufficientFunds() {
        Long senderId = 1L;
        Long recipientId = 2L;

        UserData sender = new UserData();
        UserData recipient = new UserData();

        sender.setId(senderId);
        recipient.setId(recipientId);

        BigDecimal senderBalance = BigDecimal.valueOf(200);
        BigDecimal recipientBalance = BigDecimal.valueOf(300);
        BigDecimal amount = BigDecimal.valueOf(300);

        BankAccount senderBankAcc = new BankAccount();
        senderBankAcc.setBalance(senderBalance);
        BankAccount recipientBankAcc = new BankAccount();
        recipientBankAcc.setBalance(recipientBalance);

        sender.setBankAccount(senderBankAcc);
        recipient.setBankAccount(recipientBankAcc);

        when(userRepository.findById(senderId)).thenReturn(Optional.of(sender));
        when(userRepository.findById(recipientId)).thenReturn(Optional.of(recipient));

        assertThrows(InsufficientFundsException.class, () -> transferService.transferMoney(senderId, recipientId, amount));

        assertEquals(BigDecimal.valueOf(200), sender.getBankAccount().getBalance());
        assertEquals(BigDecimal.valueOf(300), recipient.getBankAccount().getBalance());
    }
}
