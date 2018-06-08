package pl.coderslab.betting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.betting.dto.GameDto;
import pl.coderslab.betting.dto.PlayerDto;
import pl.coderslab.betting.dto.TeamDto;
import pl.coderslab.betting.dto.VideoGameDto;
import pl.coderslab.betting.entity.Game;
import pl.coderslab.betting.entity.Player;
import pl.coderslab.betting.entity.Team;
import pl.coderslab.betting.entity.VideoGame;

import java.util.ArrayList;
import java.util.List;

/**
 * this service contain methods that convert all of the data in our database to json format
 */

@Service
public class RestService {
    @Autowired
    GameService gameService;
    @Autowired
    TeamService teamService;
    @Autowired
    VideoGameService videoGameService;
    @Autowired
    PlayerService playerService;

    public List<GameDto> getAllGames() {
        List<Game> games = gameService.findAllGames();
        List<GameDto> gamesDTO = new ArrayList<>();
        for (Game game : games) {
            GameDto gameDTO = new GameDto();

            gameDTO.setId(game.getId());
            gameDTO.setDateAndTime(game.getDateAndTime());
            gameDTO.setOdd1(game.getOdd1());
            gameDTO.setOdd2(game.getOdd2());
            gameDTO.setStatus(game.getStatus());
            gamesDTO.add(gameDTO);

        }
        return gamesDTO;
    }
    public List<PlayerDto> getAllPlayers() {
        List<Player> players = playerService.findAllPlayers();
        List<PlayerDto> playersDTO = new ArrayList<>();
        for (Player player : players) {
            PlayerDto playerDto = new PlayerDto();

            playerDto.setId(player.getId());
            playerDto.setNickname(player.getNickname());
            playerDto.setFirstName(player.getFirstName());
            playerDto.setLastName(player.getLastName());
            playerDto.setWinRatio(player.getWinRatio());
            playersDTO.add(playerDto);
        }
        return playersDTO;
    }

    public List<TeamDto> getAllTeams() {
        List<Team> teams = teamService.findAllTeams();
        List<TeamDto> teamsDTO = new ArrayList<>();
        for (Team team : teams) {
            TeamDto teamDto = new TeamDto();
            teamDto.setId(team.getId());
            teamDto.setName(team.getName());
            teamDto.setWinRatio(team.getWinRatio());
            teamsDTO.add(teamDto);
        }
        return teamsDTO;
    }

    public List<VideoGameDto> getAllVideoGames() {
        List<VideoGame> videoGames = videoGameService.findAllVideoGame();
        List<VideoGameDto> videoGamesDTO = new ArrayList<>();
        for (VideoGame videoGame : videoGames ) {
            VideoGameDto videoGameDto = new VideoGameDto();
            videoGameDto.setId(videoGame.getId());
            videoGameDto.setName(videoGame.getName());
            videoGameDto.setMaxAmountOfPlayersInTeam(videoGame.getMaxAmountOfPlayersInTeam());
            videoGameDto.setNumberOfRounds(videoGame.getNumberOfRounds());
            videoGameDto.setRoundDuration(videoGame.getRoundDuration());
            videoGamesDTO.add(videoGameDto);
        }
        return videoGamesDTO;
    }


}
