package com.example.crm.auth;

/**
 * DTO pour encapsuler le JWT renvoyé au client.
 */
public record AuthResponse(String token) {
}
