package com.example.bankoperationservice.model;

import com.example.bankoperationservice.model.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;


import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "user_data")
@Data
//@ToString(exclude = {"contacts"})
@NoArgsConstructor
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Full name cannot be null")
    @NotBlank(message = "Full name cannot be blank")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Full name cannot be null")
    @NotBlank(message = "Full name cannot be blank")
    private String surname;

    @Column(unique = true)
    @NotNull(message = "Login cannot be null")
    @NotBlank(message = "Login cannot be blank")
    @Size(min = 4, max = 20, message = "Login length must be between 4 and 20 characters")
    private String login;

    @Column(nullable = false)
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(nullable = false)
    @NotNull(message = "Initial balance cannot be null")
    @DecimalMin(value = "0.00", inclusive = false, message = "Initial balance must be greater than 0")
    private BigDecimal initialBalance;

    @Column(name = "birth_date", nullable = false)
    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, columnDefinition = "role")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private BankAccount bankAccount;
}
