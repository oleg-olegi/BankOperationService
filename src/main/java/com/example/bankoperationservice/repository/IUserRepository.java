package com.example.bankoperationservice.repository;

import com.example.bankoperationservice.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface IUserRepository extends JpaRepository<UserData, Long> {
    @Query(value = "SELECT * FROM user_data  WHERE user_data.birth_date> :dateParam", nativeQuery = true)
    List<UserData> findAllByDate(@Param("dateParam") LocalDate dateParam);

    @Query(value = "SELECT * FROM user_data WHERE name LIKE concat(:name, '%')", nativeQuery = true)
    List<UserData> findAllByName(String name);

    @Query(value = "SELECT * FROM user_data  WHERE user_data.login LIKE :userName", nativeQuery = true)
    Optional<UserData> findByUserName(@Param("userName") String userName);

    boolean existsByLogin(@Param("login") String login);

    Optional<UserData> findUsersByLoginIgnoreCase(String login);

    @Modifying
    @Query("DELETE FROM UserData u WHERE u.id = :id")
    void deleteUserById(@Param("id") Long id);

    @Query(value = "SELECT * FROM user_data WHERE user_data.bank_account_account_number " +
            "LIKE :bankAccId", nativeQuery = true)
    Optional<UserData> findByBankAccountId(@Param("bankAccId") String bankAccId);
}
