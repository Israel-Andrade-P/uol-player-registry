package com.zel92.desafio_uol.controller;

import com.zel92.desafio_uol.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("players-list")
public class PlayerListController {
    private final PlayerService service;

    public PlayerListController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public String listAllPlayers(Model model){
        model.addAttribute("players", service.listPlayers());
        return "players_list";
    }
}
