package pl.coderslab.betting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.betting.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findPlayerById(Long id);
    Player findPlayerByNickname(String nickname);
    void deletePlayerById(Long id);
    long count();
}
