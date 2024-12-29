package com.zel92.desafio_uol.model;

import com.zel92.desafio_uol.enumeration.Group;

public record Player(
        String name,
        String codename,
        String email,
        Group groupName,
        String telephone) {

    public Player withCodename(String codename){
        return new Player(name, codename, email, groupName, telephone);
    }
}

