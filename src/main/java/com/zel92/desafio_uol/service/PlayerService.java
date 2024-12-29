package com.zel92.desafio_uol.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zel92.desafio_uol.enumeration.Group;
import com.zel92.desafio_uol.model.Player;
import com.zel92.desafio_uol.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository repository;
    private final CodenameService codenameService;

    public PlayerService(PlayerRepository repository, CodenameService codenameService) {
        this.repository = repository;
        this.codenameService = codenameService;
    }

    public void add(Player player) throws JsonProcessingException {
        var codenamesTaken = listAllTakenCodenames(player.groupName());
        var codename = codenameService.codenameGenerator(player.groupName(), codenamesTaken);
        var newPlayer = player.withCodename(codename);
        repository.save(newPlayer);
    }

    private List<String> listAllTakenCodenames(Group groupName) {
        return repository.listCodenamesByGroup(groupName);
    }
}
