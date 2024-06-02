package com.example.bankoperationservice.service;

import com.example.bankoperationservice.exceptions.InsufficientFundsException;
import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.repository.IBankAccountRepository;
import com.example.bankoperationservice.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TransferService {

    private IUserRepository userRepository;

    private IBankAccountRepository bankRepository;

    @Transactional
    public void transferMoney(String senderBankAccId, String recipientBankAccId, BigDecimal amount) {
        Long senderId = bankRepository.findById(senderBankAccId).get().getUserData().getId();
        Long recipientId = bankRepository.findById(recipientBankAccId).get().getUserData().getId();

        UserData sender = userRepository.findById(senderId).get();
        UserData recipient = userRepository.findById(recipientId).get();



        BigDecimal senderBalance = userRepository.findById(senderId).get().getBankAccount().getBalance();
        BigDecimal recipientBalance = userRepository.findById(recipientId).get().getBankAccount().getBalance();
        if (senderBalance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Недостаточно средств");
        }
        sender.getBankAccount().setBalance(senderBalance.subtract(amount));

        recipient.getBankAccount().setBalance(recipientBalance.add(amount));
    }
}
