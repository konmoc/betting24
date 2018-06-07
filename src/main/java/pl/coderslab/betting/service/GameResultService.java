package pl.coderslab.betting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.betting.entity.GameResult;
import pl.coderslab.betting.entity.Player;
import pl.coderslab.betting.repository.GameRepository;
import pl.coderslab.betting.repository.GameResultRepository;

import java.util.List;

@Service
public class GameResultService {
    @Autowired
    GameResultRepository gameResultRepository;
    public void saveGameResult(GameResult gameResult){
        gameResultRepository.save(gameResult);

    }
    List<GameResult> findGameResultsByPlayerWhoWon(Player player){
        return gameResultRepository.findGameResultsByPlayerWhoWon(player);
    }
}
