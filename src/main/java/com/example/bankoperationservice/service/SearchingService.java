package com.example.bankoperationservice.service;

import com.example.bankoperationservice.model.Contact;
import com.example.bankoperationservice.model.UserData;
import com.example.bankoperationservice.repository.IContactRepository;
import com.example.bankoperationservice.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SearchingService {
    private final static Logger logger = LoggerFactory.getLogger(SearchingService.class);
    private IUserRepository userRepository;
    private IContactRepository contactRepository;

    public List<UserData> searchByDate(LocalDate date) {
        return userRepository.findAllByDate(date);
    }

    public Contact searchByPhone(String phone) {
        return contactRepository.findByPhone(phone);
    }

    public List<UserData> searchByName(String name) {
        return userRepository.findAllByName(name);
    }

    public Contact searchByEmail(String email) {
        return contactRepository.findByEmail(email);
    }
}

