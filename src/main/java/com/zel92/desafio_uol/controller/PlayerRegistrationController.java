package com.zel92.desafio_uol.controller;

import com.zel92.desafio_uol.enumeration.Group;
import com.zel92.desafio_uol.model.Player;
import com.zel92.desafio_uol.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register-player")
public class PlayerRegistrationController {
    private final PlayerService service;

    public PlayerRegistrationController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public String playerRegistryPage(Model model){
        model.addAttribute("player", new Player(null, null, null, null, null));
        model.addAttribute("codenameGroups", Group.values());
        return "register_player";
    }

    @PostMapping
    public String registerPlayer(@ModelAttribute Player player) {
        try {
            service.add(player);
        } catch (Exception e) {
            return "redirect:/register-player";
        }

        return "redirect:/register-player";
    }
}
