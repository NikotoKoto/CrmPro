package com.example.crm.Contact.controller;

import com.example.crm.Contact.dto.ContactResponseDto;
import com.example.crm.Contact.dto.CreateContactRequestDto;
import com.example.crm.Contact.dto.UpdateContactRequestDto;
import com.example.crm.Contact.service.ContactService;
import com.example.crm.User.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<ContactResponseDto> createContact(
            @RequestBody CreateContactRequestDto dto,
            @RequestAttribute("user") User user) {
        ContactResponseDto response = contactService.createContact(dto, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDto>> getContacts(
            @RequestAttribute("user") User user) {
        List<ContactResponseDto> contacts = contactService.getContactsByUser(user);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDto> getContactById(
            @PathVariable Long id,
            @RequestAttribute("user") User user) {
        ContactResponseDto contact = contactService.getContactById(id, user);
        return ResponseEntity.ok(contact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDto> updateContact(
            @PathVariable Long id,
            @RequestBody UpdateContactRequestDto dto,
            @RequestAttribute("user") User user) {
        ContactResponseDto updated = contactService.updateContact(id, dto, user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(
            @PathVariable Long id,
            @RequestAttribute("user") User user) {
        contactService.deleteContact(id, user);
        return ResponseEntity.noContent().build();
    }
}
