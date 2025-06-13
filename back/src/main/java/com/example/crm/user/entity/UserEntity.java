package com.example.crm.user.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "public")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name",length = 50, nullable = false)
    private String name;
    @Column(name="firstname", length = 50,nullable = false)
    private String firstname;
    @Column(name="email")
    private String email;
    @Column(name="password",length = 50,nullable = false)
    private String password;


    public UserEntity(){}

    public UserEntity(String firstname, String name, String email, String password) {
        this.firstname = firstname;
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname(){
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
