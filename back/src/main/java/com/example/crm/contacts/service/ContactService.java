package com.example.crm.contacts.service;

import com.example.crm.contacts.entity.Contact;
import com.example.crm.contacts.repository.ContactRepository;


import com.example.crm.user.domain.User;

import com.example.crm.user.entity.UserEntity;
import com.example.crm.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    private UserRepository userRepository;

    public List<Contact> getContactsByUser(String userEmail){
        UserEntity user = userRepository.findByEmail(userEmail).orElseThrow();
        return contactRepository.findByUser(user);
    }

    public Contact createContact(Contact contact, String userEmail) {
        UserEntity user = userRepository.findByEmail(userEmail).orElseThrow();
        contact.setUser(user);
        return contactRepository.save(contact);
    }

}
