package com.zel92.desafio_uol.model;

import com.zel92.desafio_uol.enumeration.Group;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Player(
        @NotBlank
        String name,
        String codename,
        @NotBlank
        @Email
        String email,
        @NotNull
        Group groupName,
        String telephone) {

    public Player withCodename(String codename) {
        return new Player(name, codename, email, groupName, telephone);
    }
}

