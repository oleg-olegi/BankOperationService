package com.example.bankoperationservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
    private String phones;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn
    @Cascade(CascadeType.ALL)
    private UserData userData;
}

