package com.example.bankoperationservice.service;

import com.example.bankoperationservice.exceptions.BankAccountNotFoundException;
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

        UserData sender = userRepository.findByBankAccountId(senderBankAccId).orElseThrow(() -> new BankAccountNotFoundException(
                String.format("Sender's bank account with id '%s' not found", senderBankAccId)));

        UserData recipient = userRepository.findByBankAccountId(recipientBankAccId).orElseThrow(() -> new BankAccountNotFoundException(
                String.format("Recipient's bank account with id '%s' not found", senderBankAccId)));

        BigDecimal senderBalance = bankRepository.findById(sender.getBankAccount().getAccountNumber()).orElseThrow(
                () -> new BankAccountNotFoundException(
                        String.format("Sender's bank account with id '%s' not found", senderBankAccId))).getBalance();

        BigDecimal recipientBalance = bankRepository.findById(recipient.getBankAccount().getAccountNumber()).orElseThrow(
                () -> new BankAccountNotFoundException(
                        String.format("Recipient's bank account with id '%s' not found", senderBankAccId))).getBalance();
        if (senderBalance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Недостаточно средств");
        }
        sender.getBankAccount().setBalance(senderBalance.subtract(amount));
        recipient.getBankAccount().setBalance(recipientBalance.add(amount));
    }
}
