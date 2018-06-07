package pl.coderslab.betting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.betting.entity.*;
import pl.coderslab.betting.repository.PlayerRepository;
import pl.coderslab.betting.repository.TeamRepository;
import pl.coderslab.betting.service.BetService;
import pl.coderslab.betting.service.TeamService;
import pl.coderslab.betting.service.UserService;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    BetService betService;

    @GetMapping("/")
    public String showMainPage(){
        return "Home";
    }

    @GetMapping("/userAccount")
    public String showUserAccount(Model model){
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Bet> userBets = betService.findBetsByUser(user);
        List<Bet> user1v1Bets = new ArrayList<>();
        List<Bet> userTeamBets = new ArrayList<>();
        for(Bet bet : userBets){
            if(bet.getGame().getPlayersInGame().size()>0){
                user1v1Bets.add(bet);
            }else{
                userTeamBets.add(bet);
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("playerBets",user1v1Bets);
        model.addAttribute("teamBets",userTeamBets);
        return "UserAccount";
    }
    @GetMapping("/userMessages")
    public String showUserMessages(Model model){
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Message> receivedMessages = user.getMessagesReceived();
        model.addAttribute("messages", receivedMessages);
        return "Mailbox";
    }
}
