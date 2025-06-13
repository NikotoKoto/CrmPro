package com.example.crm.user.service;


import com.example.crm.user.domain.User;
import com.example.crm.user.entity.UserEntity;
import com.example.crm.user.mappeur.UserMappeur;
import com.example.crm.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {
        UserEntity entity = UserMappeur.toEntity(user);
        UserEntity saved = userRepository.save(entity);
        return UserMappeur.toDomain(saved);
    }

    public List<User> getUsers() {
        return userRepository.findAll().stream()
                .map(UserMappeur::toDomain)
                .toList();
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .map(UserMappeur::toDomain)
                .orElse(null);
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    public User updateUser(Long id, User userUpdated) {
        return userRepository.findById(id).map(u -> {
            u.setName(userUpdated.lastName());
            u.setFirstname(userUpdated.firstname());
            u.setEmail((userUpdated.email()));
            u.setPassword(userUpdated.password());
            UserEntity saved = userRepository.save(u);
            return UserMappeur.toDomain(saved);
        }).orElse(null);
    }

}
