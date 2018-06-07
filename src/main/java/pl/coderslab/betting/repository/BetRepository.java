package pl.coderslab.betting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.betting.entity.Bet;
import pl.coderslab.betting.entity.Bet;
import pl.coderslab.betting.entity.Game;
import pl.coderslab.betting.entity.User;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    
    Bet findBetById(Long id);
    void deleteBetById(Long id);
    //find all bets of users with id:
    //find all best of game with id:
    List<Bet> findAllBetsByGame(Game game);
    List<Bet> findBetsByUser(User user);
}
