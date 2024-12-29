package com.zel92.desafio_uol.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zel92.desafio_uol.web.AvengersDTO;
import com.zel92.desafio_uol.enumeration.Group;
import com.zel92.desafio_uol.web.CodenameDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.List;
@Repository
public class AvengersRepository implements CodenameRepository {
    @Override
    public CodenameDTO getCodenameList() throws JsonProcessingException {
        var codenames = RestClient
                .builder()
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN_VALUE)
                .baseUrl(Group.AVENGERS.getUri())
                .build()
                .get()
                .retrieve()
                .body(String.class);

        ObjectMapper jsonMapper = new ObjectMapper();
        var avengers = jsonMapper.readValue(codenames, AvengersDTO.class);

        return avengers;
    }
}
