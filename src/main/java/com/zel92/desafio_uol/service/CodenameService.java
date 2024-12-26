package com.zel92.desafio_uol.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zel92.desafio_uol.enumeration.Group;
import com.zel92.desafio_uol.exception.NoCodenameAvailableException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodenameService {
    private final CodenameRepositoryFactory codenameRepositoryFactory;

    public CodenameService(CodenameRepositoryFactory codenameRepositoryFactory) {
        this.codenameRepositoryFactory = codenameRepositoryFactory;
    }

    public String codenameGenerator(Group group, List<String> codenamesTaken){
        List<String> availableCodenames = listAvailableCodenames(group, codenamesTaken);
        if (availableCodenames.isEmpty()){
            throw new NoCodenameAvailableException("No codename available for group: " + group.getValue());
        }
        String codenameSelected = selectCodename(availableCodenames);
        return codenameSelected;
    }

    private List<String> listAvailableCodenames(Group group, List<String> codenamesTaken) {
       List<String> codenames = getCodenames(group);

       List<String> availableCodenames = codenames.stream().filter(codename -> !codenamesTaken.contains(codename)).toList();

        return availableCodenames;
    }

    private List<String> getCodenames(Group group) throws JsonProcessingException {
        var codenameRepository = codenameRepositoryFactory.create(group);

        return codenameRepository.getCodenameList();
    }
}
