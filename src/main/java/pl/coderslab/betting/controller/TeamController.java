package pl.coderslab.betting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.betting.entity.Team;
import pl.coderslab.betting.repository.TeamRepository;

import java.util.List;

@Controller
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/teams")
    public String showAllTeams(Model model){
        List<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        return "Teams";
    }
}
