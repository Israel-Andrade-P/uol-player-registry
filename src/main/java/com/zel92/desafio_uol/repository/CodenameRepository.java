package com.zel92.desafio_uol.repository;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CodenameRepository {
    List<String> getCodenameList() throws JsonProcessingException;
}
