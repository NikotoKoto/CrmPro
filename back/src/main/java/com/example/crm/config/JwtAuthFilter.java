package com.example.crm.config;

import com.example.crm.auth.CustomUserDetailsService;
import com.example.crm.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * ✅ Vérifie s’il y a un JWT dans la requête.
 * ✅ Peut le lire soit dans les cookies, soit dans l'en-tête Authorization.
 * ✅ Valide le JWT et charge l'utilisateur correspondant.
 * ✅ Remplit le SecurityContext pour que Spring sache qui est connecté.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1️⃣ On ignore les routes publiques (login, register...)
        String path = request.getRequestURI();
        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2️⃣ On essaie de récupérer le token dans les cookies
        String token = extractTokenFromCookie(request);

        // 3️⃣ Si pas trouvé dans les cookies, on regarde dans l'en-tête Authorization
        if (token == null) {
            token = extractTokenFromAuthorizationHeader(request);
        }

        // 4️⃣ Si on a trouvé un token et que personne n'est encore authentifié dans ce contexte
        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                // 🔹 Récupère l'email contenu dans le token
                String email = jwtService.extractUsername(token);
                System.out.println("JWT Filter email from token: '" + email + "'");

                // 🔹 Charge l'utilisateur depuis la base
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                // 🔹 Valide le token avec les infos utilisateur
                if (jwtService.validateToken(token, userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    // 🔹 Met l'authentification dans le contexte de sécurité
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                // 🔹 Si le token est invalide ou l'utilisateur introuvable, on log et on continue
                System.out.println("JWT Filter - Erreur validation token : " + e.getMessage());
            }
        }

        // 5️⃣ Passe la main au filtre suivant
        filterChain.doFilter(request, response);
    }

    /**
     * 📌 Récupère le JWT dans les cookies de la requête.
     */
    private String extractTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 📌 Récupère le JWT dans l'en-tête Authorization (format "Bearer <token>").
     */
    private String extractTokenFromAuthorizationHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
