package com.example.crm.contact.mapper;

import com.example.crm.company.entity.Company;
import com.example.crm.contact.dto.ContactResponseDto;
import com.example.crm.contact.dto.CreateContactRequestDto;
import com.example.crm.contact.dto.UpdateContactRequestDto;
import com.example.crm.contact.entity.Contact;

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
