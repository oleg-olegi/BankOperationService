package com.example.bankoperationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;

@Entity
@Data
@ToString(exclude = {"userData"})
@NoArgsConstructor
public class BankAccount {

    @Id
    @Column(name = "account_number")
    @NotNull
    private String accountNumber;

    @Column(name = "balance")
    @PositiveOrZero(message = "Balance must be positive or zero")
    private BigDecimal balance;

    @Column(name = "start_balance")
    private BigDecimal startBalance;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private UserData userData;
}
