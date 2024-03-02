package com.example.bankoperationservice.service;

import com.example.bankoperationservice.model.Contact;
import com.example.bankoperationservice.repository.IContactRepository;
import com.example.bankoperationservice.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContactService {
    private final static Logger logger = LoggerFactory.getLogger(ContactService.class);
    private IContactRepository contactRepository;

    private IUserRepository userRepository;

    public void updatePhone(Long id, String phone) {
        logger.info("Trying to refactor field Phone from user with ID: {}", id);
        Optional<Contact> foundedContact = contactRepository.findByUserId(id);
        if (foundedContact.isPresent()) {
            Contact existingContact = foundedContact.get();
            existingContact.setPhones(phone);
            contactRepository.save(existingContact);
            logger.info("User ID {} successfully updated {}.", id, existingContact);
        } else {
            logger.info("User ID {} not found.", id);
        }
    }

    public void updateEmail(Long id, String email) {
        logger.info("Trying to refactor field Email from user with ID: {}", id);
        Optional<Contact> foundedContact = contactRepository.findByUserId(id);
        if (foundedContact.isPresent()) {
            Contact existingContact = foundedContact.get();
            existingContact.setEmail(email);
            contactRepository.save(existingContact);
            logger.info("User ID {} successfully updated {}.", id, existingContact);
        } else {
            logger.info("User ID {} not found.", id);
        }
    }

    public void addPhone(Long id, String phone) {
        Collection<Contact> foundedContact = contactRepository.findAllByUserId(id);
        if (!foundedContact.isEmpty()) {

            Contact newPhone = new Contact();
            newPhone.setPhones(phone);

            newPhone.setUserData(userRepository.findById(id).get());
            contactRepository.save(newPhone);
        }
    }

    public void addEmail(Long id, String email) {
        Optional<Contact> foundedContact = contactRepository.findByUserId(id);
        if (foundedContact.isPresent()) {
            Contact newEmail = new Contact();
            newEmail.setEmail(email);
            newEmail.setUserData(userRepository.findById(id).get());
            contactRepository.save(newEmail);
        }
    }

    public void deletePhone(Long id, String phone) {
        List<Contact> foundedPhones = contactRepository.findAllByUserId(id);
        foundedPhones.forEach(contact -> {
            if (contact.getPhones().equals(phone) && contact.getEmail() == null) {
                contactRepository.delete(contact);
            } else if (contact.getPhones().equals(phone) && contact.getEmail() != null) {
                contact.setPhones(null);
                contactRepository.save(contact);
            }
        });
    }
    public void deleteEmail(Long id, String email) {
        List<Contact> foundedEmails = contactRepository.findAllByUserId(id);
        foundedEmails.forEach(contact -> {
            if (contact.getEmail().equals(email) && contact.getPhones() == null) {
                contactRepository.delete(contact);
            } else if (contact.getEmail().equals(email) && contact.getPhones() != null) {
                contact.setEmail(null);
                contactRepository.save(contact);
            }
        });
    }
}
