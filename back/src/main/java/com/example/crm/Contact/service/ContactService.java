package com.example.crm.Contact.service;

import com.example.crm.Company.repository.CompanyRepository;
import com.example.crm.Company.entity.Company;
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
    @Autowired
    CompanyRepository companyRepository;


    public ContactResponseDto createContact(CreateContactRequestDto dto, String email){

        User owner = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Company company = companyRepository.findByName(dto.getCompany().getName())
                .orElseGet(()-> {
                    Company newCompany = new Company();
                    newCompany.setName(dto.getCompany().getName());
                    return companyRepository.save(newCompany);
                });
        Contact contact = ContactMapper.toEntity(dto, company);
        contact.setOwner(owner);
        Contact saved = contactRepository.save(contact);

        return ContactMapper.toDto(saved);

    }

    public List<ContactResponseDto> getContactsByUser(String email){
        User owner = userRepository.findByEmailIgnoreCase(email)
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

        User owner = userRepository.findByEmailIgnoreCase(email)
        .orElseThrow(()-> new RuntimeException(("Contact not found")));
        if (!contact.getOwner().equals(owner)) {
            throw new RuntimeException("Unauthorized");
        }
        Company company = companyRepository.findByName(dto.getCompany().getName())
                .orElseThrow(()-> new RuntimeException("Company not found"));

        ContactMapper.toEntity(dto, contact, company);
        Contact saved = contactRepository.save(contact);

        return ContactMapper.toDto(saved);
    }

    public void deleteContact(Long id, String email) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        User owner = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!contact.getOwner().equals(owner)) {
            throw new RuntimeException("Unauthorized");
        }

        contactRepository.delete(contact);
    }

}
