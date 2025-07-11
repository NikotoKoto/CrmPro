package com.example.crm.contacts;

public class ContactEntity {
    @Entity
    @Table(name = "contacts")
    public class ContactEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String firstname;
        private String email;
        private String phone;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private UserEntity user;

        public ContactEntity() {}

        public ContactEntity(String firstname, String email, String phone, UserEntity user) {
            this.firstname = firstname;
            this.email = email;
            this.phone = phone;
            this.user = user;
        }

        // Getters & Setters
        public Long getId() { return id; }

        public String getFirstname() { return firstname; }
        public void setFirstname(String firstname) { this.firstname = firstname; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public UserEntity getUser() { return user; }
        public void setUser(UserEntity user) { this.user = user; }
    }
}
