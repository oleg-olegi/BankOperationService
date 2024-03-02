package com.example.bankoperationservice.service;

import com.example.bankoperationservice.repository.IBankAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Component
public class BalanceIncreaseScheduler {
    private final static Logger logger = LoggerFactory.getLogger(BalanceIncreaseScheduler.class);
    private final IBankAccountRepository bankAccountRepository;

    public BalanceIncreaseScheduler(IBankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void increaseBalance() {
        try {
            bankAccountRepository.balanceLessThenLimit().forEach(bankAccount -> {
                BigDecimal currentBalance = bankAccount.getBalance();
                BigDecimal increaseAmount = currentBalance.multiply(BigDecimal.valueOf(0.05));
                BigDecimal newBalance = currentBalance.add(increaseAmount);
                bankAccount.setBalance(newBalance);
                bankAccountRepository.save(bankAccount);
                logger.info("Баланс банковского счета успешно увеличен");
            });
        } catch (Exception e) {
            logger.error("Ошибка при увеличении баланса банковских счетов", e);
        }
    }
}

