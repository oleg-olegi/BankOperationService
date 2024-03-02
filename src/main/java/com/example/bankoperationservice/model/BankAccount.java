package com.example.bankoperationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@ToString(exclude = {"userData"})
@NoArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero(message = "Balance must be positive or zero")
    private BigDecimal balance;

    private BigDecimal startBalance;

    private String accountNumber;
    @JsonIgnore
    @OneToOne(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private UserData userData;
}
