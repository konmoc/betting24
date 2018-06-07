package pl.coderslab.betting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.betting.entity.Team;
import pl.coderslab.betting.repository.TeamRepository;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public void saveTeam(Team team){
        teamRepository.save(team);
    }

    public Team findTeamById(Long id){
        return teamRepository.findTeamById(id);
    }

    public void deleteTeamWithId(Long id){
        teamRepository.deleteTeamById(id);
    }

    public List<Team> findAllTeams(){
        return teamRepository.findAll();
    }


}
