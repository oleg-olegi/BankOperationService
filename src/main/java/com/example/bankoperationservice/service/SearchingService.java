package com.example.bankoperationservice.service;

import com.example.bankoperationservice.dto.UserDTO;
import com.example.bankoperationservice.model.Contact;
import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.repository.IContactRepository;
import com.example.bankoperationservice.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
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



    public List<UserDTO> searchByDate(LocalDate date) {
        logger.info("Searching users by date: {}", date);
        List<UserData> users = userRepository.findAllByDate(date);
        List<UserDTO> dtoList = new ArrayList<>();
        users.forEach(userData -> dtoList
                .add(new UserDTO(userData.getId(), userData.getName(), userData.getSurname(),
                        userData.getDateOfBirth())));
        return dtoList;
    }

    public UserDTO searchByPhone(String phone) {
        logger.info("Searching user by phone: {}", phone);
        Contact contacts = contactRepository.findByPhone(phone);
        return new UserDTO(contacts.getId(), contacts.getUserData().getName(),
                contacts.getUserData().getSurname(), contacts.getUserData().getDateOfBirth());
    }

    public List<UserDTO> searchByName(String name) {
        logger.info("Searching users by name: {}", name);
        List<UserDTO> dtoList = new ArrayList<>();
        userRepository.findAllByName(name).forEach(userData -> dtoList
                .add(new UserDTO(userData.getId(), userData.getName(), userData.getSurname(),
                        userData.getDateOfBirth())));
        return dtoList;
    }

    public UserDTO searchByEmail(String email) {
        logger.info("Searching user by email: {}", email);
        Contact contacts = contactRepository.findByEmail(email);
        return new UserDTO(contacts.getId(), contacts.getUserData().getName(),
                contacts.getUserData().getSurname(), contacts.getUserData().getDateOfBirth());
    }

    public List<UserData> getAll(Integer page, Integer size) {
        logger.info("Getting all users with pagination (page: {}, size: {})", page, size);
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAll(pageRequest).getContent();
    }
}

