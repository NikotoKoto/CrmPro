package com.example.crm.auth.controller;

import com.example.crm.auth.dto.AuthRequest;
import com.example.crm.auth.service.AuthentificationService;
import com.example.crm.user.domain.User;
import com.example.crm.user.dto.UserForm;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    AuthentificationService authService;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserForm userForm, HttpServletResponse response){
       User user = authService.register(userForm,response);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/login")
    public ResponseEntity<User> lon(@RequestBody AuthRequest request, HttpServletResponse response){
        User user = authService.login(request.email(), request.password(), response);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(@CookieValue(name = "jwt", required = false) String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(401).build(); // Pas de token => pas connecté
        }

        try {
            User user = authService.getCurrentUser(token);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(401).build(); // Token invalide ou utilisateur non trouvé
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response){
        ResponseCookie cookie = ResponseCookie.from("jwt","")
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());
        return ResponseEntity.ok("Déconnecté");
    }
}
