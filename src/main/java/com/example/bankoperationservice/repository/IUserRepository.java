package com.example.bankoperationservice.repository;

import com.example.bankoperationservice.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface IUserRepository extends JpaRepository<UserData, Long> {
    @Query(value = "SELECT * FROM user_data  WHERE date_of_birth> :dateParam", nativeQuery = true)
    List<UserData> findAllByDate(@Param("dateParam") LocalDate dateParam);

    @Query(value = "select * from user_data where full_name like concat(:name, '%')", nativeQuery = true)
    List<UserData> findAllByName(String name);
}
