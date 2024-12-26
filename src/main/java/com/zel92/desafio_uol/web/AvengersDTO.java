package com.zel92.desafio_uol.web;

import java.util.List;

public record AvengersDTO(List<Codinome> vingadores) implements CodenameDTO{
    @Override
    public List<String> getCodenames() {
        return vingadores.stream().map(Codinome::codinome).toList();
    }
}

record Codinome(String codinome){}
