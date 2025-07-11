package com.example.crm.user;


import com.example.crm.contacts.ContactEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactEntity> contacts;

    //Constructors
    public UserEntity(){}

    public UserEntity(String firstname, String email, String password, Role role){
        this.firstname = firstname;
        this.email =email;
        this.password = password;
        this.role = role;
    }

    //Getter & Setter

    public Long getId(){
        return id;
    }


    public String  getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Role getRole(){
        return role;
    }
    public void setRole(Role role){
        this.role = role;
    }

    public List<ContactEntity> getContacts(){
        return contacts;
    }
    public void setContact(List<ContactEntity> contacts){
        this.contacts = contacts;
    }

}
