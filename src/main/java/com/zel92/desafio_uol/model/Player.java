package com.zel92.desafio_uol.model;

import com.zel92.desafio_uol.enumeration.Group;

public record Player(
        Long id,
        String name,
        String codename,
        String email,
        Group group,
        String telephone) {}
