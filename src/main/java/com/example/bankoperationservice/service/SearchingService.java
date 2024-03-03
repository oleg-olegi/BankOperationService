package com.example.bankoperationservice.service;

import com.example.bankoperationservice.dto.DTO;
import com.example.bankoperationservice.model.Contact;
import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.repository.IContactRepository;
import com.example.bankoperationservice.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchingService {
    private final static Logger logger = LoggerFactory.getLogger(SearchingService.class);
    private IUserRepository userRepository;
    private IContactRepository contactRepository;


    public List<DTO> searchByDate(LocalDate date) {
        List<UserData> users = userRepository.findAllByDate(date);
        List<DTO> dtoList = new ArrayList<>();
        users.forEach(userData -> dtoList
                .add(new DTO(userData.getId(), userData.getFullName(), userData.getDateOfBirth())));
        return dtoList;
    }


    public DTO searchByPhone(String phone) {
        Contact contacts = contactRepository.findByPhone(phone);
        return new DTO(contacts.getId(), contacts.getUserData().getFullName(), contacts.getUserData().getDateOfBirth());
    }

    public List<DTO> searchByName(String name) {
        List<DTO> dtoList = new ArrayList<>();
        userRepository.findAllByName(name).forEach(userData -> dtoList
                .add(new DTO(userData.getId(), userData.getFullName(), userData.getDateOfBirth())));
        return dtoList;
    }

    public DTO searchByEmail(String email) {
        Contact contacts = contactRepository.findByEmail(email);
        return new DTO(contacts.getId(), contacts.getUserData().getFullName(), contacts.getUserData().getDateOfBirth());
    }
}

