package com.zel92.desafio_uol.service;

import com.zel92.desafio_uol.enumeration.Group;
import com.zel92.desafio_uol.repository.AvengersRepository;
import com.zel92.desafio_uol.repository.CodenameRepository;
import com.zel92.desafio_uol.repository.LeagueOfJusticeRepository;
import org.springframework.stereotype.Component;

@Component
public class CodenameRepositoryFactory {
    private final AvengersRepository avengersRepository;
    private final LeagueOfJusticeRepository leagueOfJusticeRepository;

    public CodenameRepositoryFactory(AvengersRepository avengersRepository, LeagueOfJusticeRepository leagueOfJusticeRepository) {
        this.avengersRepository = avengersRepository;
        this.leagueOfJusticeRepository = leagueOfJusticeRepository;
    }

    public CodenameRepository create(Group group){
        return switch (group){
            case AVENGERS -> avengersRepository;
            case LEAGUE_OF_JUSTICE -> leagueOfJusticeRepository;
        };
    }
}
