package com.example.crm.auth;

import com.example.crm.User.entity.User;
import com.example.crm.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * Inscrit un nouvel utilisateur en base et retourne un JWT
     */
    public AuthResponse register(AuthRequest request){
        User user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setFirstname(request.firstname());
        user.setName(request.name());
        userRepository.save(user);
        String token= jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    /**
     * Authentifie un utilisateur avec email & mot de passe
     * et retourne un JWT s'il est valide
     */
    public AuthResponse login(AuthRequest request){
        User user = userRepository.findByEmail(request.email())
        .orElseThrow(() -> new RuntimeException("Invalids Credentials"));
    if(!passwordEncoder.matches(request.password(), user.getPassword())){
        throw new RuntimeException("Invalid password");
    }
    String token = jwtService.generateToken(user);
    return new AuthResponse(token);
    }
}
