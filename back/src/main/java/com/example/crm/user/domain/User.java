package com.example.crm.user.domain;

import jakarta.validation.constraints.NotBlank;

public record User(@NotBlank String firstname, @NotBlank String lastName,@NotBlank String email, @NotBlank String password ) {
}
