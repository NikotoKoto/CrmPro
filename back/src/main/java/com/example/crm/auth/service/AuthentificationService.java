package com.example.crm.auth.service;

import com.example.crm.auth.security.JwtService;

import com.example.crm.user.domain.User;
import com.example.crm.user.dto.UserForm;
import com.example.crm.user.entity.UserEntity;
import com.example.crm.user.mappeur.UserFormMapper;

import com.example.crm.user.mappeur.UserMappeur;
import com.example.crm.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public User register (UserForm userForm, HttpServletResponse response){
        //Sauvegarde en base user enregistré
        UserEntity userEntity = UserFormMapper.toEntity(userForm);
        UserEntity savedUser = userRepository.save(userEntity);


        //Generation du token
        String token = jwtService.generateToken(savedUser.getEmail());

        //Ajout d'un token dans le cookie
        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .path("/")
                .maxAge(3600)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return UserMappeur.toDomain(savedUser);
    }

    public User login(String email, String password, HttpServletResponse response ){
        // On recupe l'email
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
        // On check si le password correspond
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Invalid password");
        }
        //On genere un token
        String token = jwtService.generateToken(user.getEmail());
        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .path("/")
                .maxAge(3600)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return UserMappeur.toDomain(user);
    }

    public User getCurrentUser(String token) {
        // 1. Extrait l'email depuis le token
        String email = jwtService.extractEmail(token);

        // 2. Recherche l'utilisateur
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Crée un objet UserDetails (souvent l'entité implémente UserDetails)
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities("USER") // ou userEntity.getRoles() si tu en as
                .build();

        // 4. Vérifie le token
        if (!jwtService.isTokenValid(token, userDetails)) {
            throw new RuntimeException("Invalid token");
        }

        // 5. Retourne l'utilisateur sous forme de domaine
        return UserMappeur.toDomain(userEntity);
    }

}
