package pl.coderslab.betting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.betting.entity.Player;
import pl.coderslab.betting.repository.PlayerRepository;

import java.util.List;

@Controller
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/players")
    public String showAllPlayers(Model model){
        List<Player> players = playerRepository.findAll();
        model.addAttribute("players", players);
        return "Players";
    }
}
