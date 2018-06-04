package pl.coderslab.betting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.betting.entity.Player;
import pl.coderslab.betting.entity.Team;
import pl.coderslab.betting.entity.User;
import pl.coderslab.betting.repository.PlayerRepository;
import pl.coderslab.betting.repository.TeamRepository;
import pl.coderslab.betting.service.TeamService;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Action;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/")
    public String showMainPage(){
        return "Home";
    }

    @GetMapping("/teams")
    public String showAllTeams(Model model){
        List<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        return "Teams";
    }

    @GetMapping("/players")
    public String showAllPlayers(Model model){
        List<Player> players = playerRepository.findAll();
        model.addAttribute("players", players);
        return "Players";
    }


//    @ModelAttribute("loggedUser")
//    public User user(){
//        HttpSession session =
//        User user = (User) session.getAttribute("currentSessionUser");
//
//        if (user != null) {
//            String userName = user.getUserName();
//            // ...
//        }
//    }

//    @ModelAttribute("username")
//    public String currentUsername(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            String currentUserName = authentication.getName();
//            return currentUserName;
//        }
//        return "NO_USER";
//    }
}
