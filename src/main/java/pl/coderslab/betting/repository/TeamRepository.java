package pl.coderslab.betting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.betting.entity.Team;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findTeamById(Long id);
    Team findTeamByName(String name);
    void deleteTeamById(Long id);
}
