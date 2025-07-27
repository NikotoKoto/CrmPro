package com.example.crm.Contact.service;

import com.example.crm.Company.repository.CompanyRepository;
import com.example.crm.Company.entity.Company;
import com.example.crm.Contact.dto.ContactResponseDto;
import com.example.crm.Contact.dto.CreateContactRequestDto;
import com.example.crm.Contact.dto.UpdateContactRequestDto;
import com.example.crm.Contact.entity.Contact;
import com.example.crm.Contact.repository.ContactRepository;
import com.example.crm.User.entity.User;
import com.example.crm.User.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {
    @Mock
    private ContactRepository contactRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ContactService contactService;

    @Test
    void shouldCreateContactSuccessFully(){
        Company company = new Company("Lacoste");

        User user = new User();
        user.setId(1L);
        user.setEmail("user@email.com");

        CreateContactRequestDto createDto = new CreateContactRequestDto();
                createDto.setName("Jean");
                createDto.setEmail("jean@email.com");
                createDto.setPhone("0600000000");
                createDto.setCompany("Lacoste");
        ;

        Contact savedContact = new Contact();
        savedContact.setId(1L);
        savedContact.setName("Jean");
        savedContact.setEmail("jean@email.com");
        savedContact.setPhone("0600000000");
        savedContact.setCompany(company);

        when(companyRepository.findByName("Lacoste")).thenReturn(Optional.of(company));
        when(contactRepository.save(any(Contact.class))).thenReturn(savedContact);
        when(userRepository.findByEmailIgnoreCase("user@email.com")).thenReturn(Optional.of(user));

        // Act
        ContactResponseDto result = contactService.createContact(createDto, "user@email.com");

        // Assert
        assertNotNull(result);
        assertEquals("Jean", result.getName());
        assertEquals("Lacoste", result.getCompany().getName());
        assertEquals("0600000000", result.getPhone());
    }

    @Test
   void ShouldGetContactByUserSuccessFully(){
       User user = new User();
       user.setId(1L);
       user.setEmail("toto@gmail.com");

        Contact contact1 = new Contact();
        contact1.setName("Jean");
        contact1.setEmail("jean@email.com");
        contact1.setPhone("0600000000");
        contact1.setOwner(user);

        Contact contact2 = new Contact();
        contact2.setName("Marie");
        contact2.setEmail("marie@email.com");
        contact2.setPhone("0600000001");
        contact2.setOwner(user);

        List<Contact> mockContacts = List.of(contact1, contact2);

       when(userRepository.findByEmailIgnoreCase("toto@gmail.com")).thenReturn(Optional.of(user));
        when(contactRepository.findByOwner(user)).thenReturn(mockContacts);

       /*Act*/

       List<ContactResponseDto> result = contactService.getContactsByUser("toto@gmail.com");

       /*Assert*/
        assertNotNull(result);
        assertEquals("Jean", result.get(0).getName());
        assertEquals("Marie", result.get(1).getName());
    }

    @Test
    void ShouldUpdateContactSuccessFully(){
        User user = new User();
        user.setId(1L);
        user.setEmail("toto@gmail.com");

        Company company = new Company("Lacoste");

        UpdateContactRequestDto updateDto = new UpdateContactRequestDto();
        updateDto.setName("Jean");
        updateDto.setEmail("jean@email.com");
        updateDto.setPhone("0600000000");
        updateDto.setCompany("Lacoste");

        Contact updateContact = new Contact();
        updateContact.setId(1L);
        updateContact.setName("Jean");
        updateContact.setEmail("jean@email.com");
        updateContact.setPhone("0600000000");
        updateContact.setCompany(company);
        updateContact.setOwner(user);

        when(userRepository.findByEmailIgnoreCase("toto@gmail.com")).thenReturn(Optional.of(user));
        when(contactRepository.save(any(Contact.class))).thenReturn(updateContact);
        when(companyRepository.findByName("Lacoste")).thenReturn(Optional.of(company));
        when(contactRepository.findById(1L)).thenReturn(Optional.of(updateContact));
        /*Act*/
        ContactResponseDto result = contactService.updateContact(1L,updateDto,"toto@gmail.com");

        /*Assert*/
        assertNotNull(result);
        assertEquals("Jean", result.getName());
        assertEquals("Lacoste", result.getCompany().getName());
    }

    @Test
    void ShouldDeleteContactSuccessFully(){
        User user = new User();
        user.setId(1L);
        user.setEmail("Toto@gmail.com");

        Contact contact1 = new Contact();
        contact1.setName("Jean");
        contact1.setEmail("jean@email.com");
        contact1.setPhone("0600000000");
        contact1.setOwner(user);
        contact1.setId(1L);

        when(userRepository.findByEmailIgnoreCase("Toto@gmail.com")).thenReturn(Optional.of(user));
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact1));



        /*Act*/
        contactService.deleteContact(1L,"Toto@gmail.com");

        /*Assert*/
        verify(contactRepository).delete(contact1);

    }
}
