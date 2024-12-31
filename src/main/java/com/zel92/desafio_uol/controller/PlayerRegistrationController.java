package com.zel92.desafio_uol.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zel92.desafio_uol.enumeration.Group;
import com.zel92.desafio_uol.exception.NoCodenameAvailableException;
import com.zel92.desafio_uol.model.Player;
import com.zel92.desafio_uol.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String playerRegistryPage(Model model) {
        return getViewAndModel(model, new Player(null, null, null, null, null));
    }

    @PostMapping
    public String registerPlayer(@ModelAttribute @Valid Player player, BindingResult result, Model model) throws JsonProcessingException {
        if (result.hasErrors()) {
            return getViewAndModel(model, player);
        }
        try {
            service.add(player);
            return "redirect:/players-list";
        }catch (NoCodenameAvailableException e){
            result.rejectValue("groupName", "groupCodenameUnavailable", e.getMessage());
            return getViewAndModel(model, player);
        }
    }

    private String getViewAndModel(Model model, Player player) {
        model.addAttribute("player", player);
        model.addAttribute("codenameGroups", Group.values());
        return "register_player";
    }
}
