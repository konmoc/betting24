package pl.coderslab.betting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.betting.dto.GameDto;
import pl.coderslab.betting.dto.PlayerDto;
import pl.coderslab.betting.dto.TeamDto;
import pl.coderslab.betting.dto.VideoGameDto;
import pl.coderslab.betting.entity.Game;
import pl.coderslab.betting.service.RestService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/restApi")
public class RestController {

    @Autowired
    RestService restService;


    @GetMapping(path= "/games")
    @ResponseBody
    public List<GameDto> findAllGames() {
        return restService.getAllGames();
    }
    @GetMapping(path= "/players")
    @ResponseBody
    public List<PlayerDto> findAllPlayers() {
        return restService.getAllPlayers();
    }
    @GetMapping(path= "/teams")
    @ResponseBody
    public List<TeamDto> findAllTeams() {
        return restService.getAllTeams();
    }

    @GetMapping(path= "/videogames")
    @ResponseBody
    public List<VideoGameDto> findAllVideoGames() {
        return restService.getAllVideoGames();
    }
}
