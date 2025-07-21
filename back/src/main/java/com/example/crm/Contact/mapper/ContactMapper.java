package com.example.crm.Contact.mapper;

import com.example.crm.Company.entity.Company;
import com.example.crm.Contact.dto.ContactResponseDto;
import com.example.crm.Contact.dto.CreateContactRequestDto;
import com.example.crm.Contact.dto.UpdateContactRequestDto;
import com.example.crm.Contact.entity.Contact;

public class ContactMapper {

    public static Contact toEntity(CreateContactRequestDto dto, Company company){
        Contact contact = new Contact();
        contact.setEmail(dto.getEmail());
        contact.setName(dto.getName());
        contact.setPhone(dto.getPhone());
        contact.setCompany(company);
        return contact;
    }
    public static Contact toEntity(UpdateContactRequestDto dto,Contact contact,Company company){
        contact.setEmail(dto.getEmail());
        contact.setName(dto.getName());
        contact.setPhone(dto.getPhone());
        contact.setCompany(company);
        return contact;
    }
    public static ContactResponseDto toDto(Contact contact) {
        ContactResponseDto dto = new ContactResponseDto();
        dto.setId(contact.getId());
        dto.setName(contact.getName());
        dto.setEmail(contact.getEmail());
        dto.setPhone(contact.getPhone());
        dto.setCompany(contact.getCompany());
        return dto;
    }

}
