package pl.coderslab.betting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.betting.entity.Game;
import pl.coderslab.betting.entity.GameResult;
import pl.coderslab.betting.entity.Player;

import java.util.List;

@Repository
public interface GameResultRepository extends JpaRepository<GameResult, Long> {
    GameResult findGameResultById(Long id);
    void deleteGameResultById(Long id);
    List<GameResult> findGameResultsByPlayerWhoWon(Player player);
}
