package pl.coderslab.betting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.betting.entity.Game;
import pl.coderslab.betting.entity.Player;
import pl.coderslab.betting.entity.Team;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findGameById(Long id);
//    Game findGameByTeamsInGame(List<Team> teams);
    void deleteGameById(Long id);
    long count();
    List<Game> findGamesByPlayersInGameIsNotNull();
    List<Game> findGamesByTeamsInGameIsNotNull();
    List<Game> findAll();
    List<Game> findGamesByPlayersInGameContaining(Player player);
    List<Game> findGamesByStatus(String status);
}
