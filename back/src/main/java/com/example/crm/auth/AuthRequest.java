package com.example.crm.auth;

/**
 *  simple DTO pour représenter les données envoyées dans les requêtes POST.
 */
public record AuthRequest(String email, String password, String name, String firstname) {
}
