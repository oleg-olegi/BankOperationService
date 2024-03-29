package com.example.bankoperationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;



@Entity
@Data
@ToString(exclude = {"contacts"})
@NoArgsConstructor
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Full name cannot be null")
    @NotBlank(message = "Full name cannot be blank")
    private String fullName;

    @Column(unique = true)
    @NotNull(message = "Login cannot be null")
    @NotBlank(message = "Login cannot be blank")
    @Size(min = 4, max = 20, message = "Login length must be between 4 and 20 characters")
    private String login;

    @Column(nullable = false)
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private char[] password;

    @Column(nullable = false)
    @NotNull(message = "Initial balance cannot be null")
    @DecimalMin(value = "0.00", inclusive = false, message = "Initial balance must be greater than 0")
    private BigDecimal initialBalance;

    @Column(nullable = false)
    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Transient
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @Transient
    @Column(unique = true)
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private BankAccount bankAccount;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private Collection<Contact> contacts;
 }
