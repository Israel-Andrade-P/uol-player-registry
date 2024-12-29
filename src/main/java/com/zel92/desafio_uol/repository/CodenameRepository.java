package com.zel92.desafio_uol.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zel92.desafio_uol.web.CodenameDTO;

import java.util.List;

public interface CodenameRepository {
    CodenameDTO getCodenameList() throws JsonProcessingException;
}
