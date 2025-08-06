package com.example.crm.contact.controller;

import com.example.crm.contact.dto.ContactResponseDto;
import com.example.crm.contact.dto.CreateContactRequestDto;
import com.example.crm.contact.dto.UpdateContactRequestDto;
import com.example.crm.contact.service.ContactService;
import com.example.crm.User.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<ContactResponseDto> createContact(
            @RequestBody CreateContactRequestDto dto,
            @AuthenticationPrincipal UserDetails userDetails) {

        System.out.println("DTO reçu côté backend : " + dto);
        ContactResponseDto response = contactService.createContact(dto, userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDto>> getContacts(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<ContactResponseDto> contacts = contactService.getContactsByUser(userDetails.getUsername());
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDto> getContactById(
            @PathVariable Long id,
            @RequestAttribute("user") User user) {
        ContactResponseDto contact = contactService.getContactById(id, user);
        return ResponseEntity.ok(contact);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ContactResponseDto> updateContact(
            @PathVariable Long id,
            @RequestBody UpdateContactRequestDto dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        ContactResponseDto updated = contactService.updateContact(id, dto, userDetails.getUsername());
        return ResponseEntity.ok(updated);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        contactService.deleteContact(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
