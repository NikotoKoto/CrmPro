package com.example.crm.contacts.controller;

import com.example.crm.contacts.entity.Contact;
import com.example.crm.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/contact")
@CrossOrigin
public class ContactController {

    @Autowired
    private ContactService contactService;


    @GetMapping
    public List<Contact> getContact(Principal principal){
        return this.contactService.getContactsByUser(principal.getName());
    }

    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }


    @PostMapping
    public Contact createContact(@RequestBody Contact contact, Principal principal){
         return this.contactService.createContact(contact, principal.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id){

    }
}
