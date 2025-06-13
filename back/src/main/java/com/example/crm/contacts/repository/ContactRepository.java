package com.example.crm.contacts.repository;

import com.example.crm.contacts.entity.Contact;

import com.example.crm.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long>  {
    List<Contact> findByUser(UserEntity user);
}
