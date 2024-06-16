package com.example.bankoperationservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@ToString(exclude = {"userData"})
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String phones;

    @Column(unique = true)
    @NotNull
    private String email;

    @ManyToOne
    @JoinColumn
    private UserData userData;
}

