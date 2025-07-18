package com.example.crm.Contact.service;

import com.example.crm.Contact.dto.ContactResponseDto;
import com.example.crm.Contact.dto.CreateContactRequestDto;
import com.example.crm.Contact.dto.UpdateContactRequestDto;
import com.example.crm.Contact.entity.Contact;
import com.example.crm.Contact.mapper.ContactMapper;
import com.example.crm.Contact.repository.ContactRepository;
import com.example.crm.User.entity.User;
import com.example.crm.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    UserRepository userRepository;


    public ContactResponseDto createContact(CreateContactRequestDto dto, String email){

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        
        Contact contact = ContactMapper.toEntity(dto);
        contact.setOwner(owner);
        Contact saved = contactRepository.save(contact);

        return ContactMapper.toDto(saved);

    }

    public List<ContactResponseDto> getContactsByUser(String email){
        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return contactRepository.findByOwner(owner)
                .stream()
                .map(ContactMapper:: toDto)
                .toList();
    }
    public ContactResponseDto getContactById(Long id, User owner) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        if (!contact.getOwner().equals(owner)) {
            throw new RuntimeException("Unauthorized");
        }

        return ContactMapper.toDto(contact);
    }

    public ContactResponseDto updateContact(Long id, UpdateContactRequestDto dto, String email) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        User owner = userRepository.findByEmail(email)
        .orElseThrow(()-> new RuntimeException(("Contact not found")));
        if (!contact.getOwner().equals(owner)) {
            throw new RuntimeException("Unauthorized");
        }

        ContactMapper.toEntity(dto, contact);
        Contact saved = contactRepository.save(contact);

        return ContactMapper.toDto(saved);
    }

    public void deleteContact(Long id, String email) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!contact.getOwner().equals(owner)) {
            throw new RuntimeException("Unauthorized");
        }

        contactRepository.delete(contact);
    }

}
