package com.example.bankoperationservice.service;

import com.example.bankoperationservice.exceptions.InsufficientFundsException;
import com.example.bankoperationservice.exceptions.UserNotFoundException;
import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.repository.IBankAccountRepository;
import com.example.bankoperationservice.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TransferService {

    private IUserRepository userRepository;

    private IBankAccountRepository bankRepository;

    @Transactional
    public void transferMoney(Long senderId, Long recipientId, BigDecimal amount) {
        UserData sender = userRepository.findById(senderId).get();
        UserData recipient = userRepository.findById(recipientId).get();
        if (sender == null || recipient == null) {
            throw new UserNotFoundException("User not found");
        }
        BigDecimal senderBalance = userRepository.findById(senderId).get().getBankAccount().getBalance();
        BigDecimal recipientBalance = userRepository.findById(recipientId).get().getBankAccount().getBalance();
        if (senderBalance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        sender.getBankAccount().setBalance(senderBalance.subtract(amount));

        recipient.getBankAccount().setBalance(recipientBalance.add(amount));
    }
}
