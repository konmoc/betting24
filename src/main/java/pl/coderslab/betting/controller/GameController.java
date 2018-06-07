package pl.coderslab.betting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.betting.entity.Game;
import pl.coderslab.betting.service.GameService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/singleGames")
    public String showAll1v1Games(Model model){
        List<Game> games = gameService.findAllGames();

        List<Game> actual = new ArrayList<>();
        for(Game game : games){
            if((game.getStatus().contains("STARTED") || game.getStatus().contains("PLANNED"))
                    && game.getPlayersInGame().size()>0){
                actual.add(game);
            }
        }
        model.addAttribute("games1v1", actual);
        return "1v1Games";
    }

    @GetMapping("/teamGames")
    public String showAllTeamGames(Model model){
        List<Game> games = gameService.findAllGames();

        List<Game> actual = new ArrayList<>();
        for(Game game : games){
            if((game.getStatus().contains("STARTED") || game.getStatus().contains("PLANNED"))
                    && game.getPlayersInGame().size()==0){
                actual.add(game);
            }
        }
        model.addAttribute("gamesTeam", actual);
        return "TeamGames";
    }






}
