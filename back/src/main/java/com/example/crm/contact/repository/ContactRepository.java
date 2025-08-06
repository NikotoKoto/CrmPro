package com.example.crm.contact.repository;

import com.example.crm.contact.entity.Contact;
import com.example.crm.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByOwner(User owner);
}
