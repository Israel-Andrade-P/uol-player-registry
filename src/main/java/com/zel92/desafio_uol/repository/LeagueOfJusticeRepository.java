package com.zel92.desafio_uol.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.zel92.desafio_uol.enumeration.Group;
import com.zel92.desafio_uol.web.LeagueOfJusticeDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.List;
@Repository
public class LeagueOfJusticeRepository implements CodenameRepository{
    @Override
    public List<String> getCodenameList() throws JsonProcessingException {
        var codenames = RestClient
                .builder()
                .baseUrl(Group.LEAGUE_OF_JUSTICE.getUri())
                .build()
                .get()
                .retrieve()
                .body(String.class);

        var xmlMapper = new XmlMapper();
        var leagueOfJustice = xmlMapper.readValue(codenames, LeagueOfJusticeDTO.class);

        return leagueOfJustice.getCodenames();
    }
}
