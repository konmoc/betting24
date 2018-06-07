package pl.coderslab.betting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.betting.entity.Game;
import pl.coderslab.betting.entity.GameResult;
import pl.coderslab.betting.entity.Player;
import pl.coderslab.betting.repository.PlayerRepository;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    GameResultService gameResultService;
    @Autowired
    GameService gameService;



    public void savePlayer(Player player){
        playerRepository.save(player);
    }

    public Player findPlayerById(Long id){
        return playerRepository.findPlayerById(id);
    }

    public void deletePlayerWithId(Long id){
        playerRepository.deletePlayerById(id);
    }

    public long countAllPlayers(){
        return playerRepository.count();
    }




}
