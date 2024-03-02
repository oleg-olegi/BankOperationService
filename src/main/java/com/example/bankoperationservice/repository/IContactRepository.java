package com.example.bankoperationservice.repository;

import com.example.bankoperationservice.model.Contact;
import com.example.bankoperationservice.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public interface IContactRepository extends JpaRepository<Contact, Long> {
    @Query("select c FROM Contact c where c.userData.id = :id")
    List<Contact> findAllByUserId(@Param("id") Long id);

    @Query("select c FROM Contact c where c.userData.id = :id")
    Optional<Contact> findByUserId(@Param("id") Long id);

    @Query(value = "SELECT * FROM contact WHERE phones =:phone", nativeQuery = true)
    Contact findByPhone(String phone);
    @Query(value = "SELECT * FROM contact WHERE email =:email", nativeQuery = true)
    Contact findByEmail(String email);
}
