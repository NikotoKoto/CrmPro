package com.example.crm.config;

import com.example.crm.User.entity.User;
import com.example.crm.User.repository.UserRepository;
import com.example.crm.auth.CustomUserDetailsService;
import com.example.crm.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * ✅ Vérifie s’il y a un JWT dans la requête.
 * ✅ Vérifie que le JWT est valide et récupère l’email.
 * ✅ Charge les détails de l’utilisateur avec CustomUserDetailsService.
 * ✅ Remplit le SecurityContext pour que Spring sache qui est connecté.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtService jwtService, UserRepository userRepository, CustomUserDetailsService userDetailsService){
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;

    }

    /**
     * Filtre exécuté à chaque requête entrante
     * - Vérifie s'il y a un JWT dans les cookies
     * - Si oui, valide le JWT et récupère l'utilisateur
     * - Remplit le SecurityContext pour que Spring sache qui est connecté
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException{
    String token = extractTokenFromRequest(request);
        if (token != null) {
            String email = jwtService.extractUsername(token);
            if (email != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                System.out.println("JWT Filter email from token: '" + email + "'");
                if (jwtService.validateToken(token, userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }


    }
    filterChain.doFilter(request, response);


    }
    /**
     * Extrait le JWT du cookie
     */
    private String extractTokenFromRequest(HttpServletRequest request){
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie cookie : cookies){
            if("jwt".equals(cookie.getName())){
                return cookie.getValue();
            }
        }
    }
    return null;
    }

}
