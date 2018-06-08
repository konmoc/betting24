package pl.coderslab.betting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.betting.entity.Bet;
import pl.coderslab.betting.entity.Game;
import pl.coderslab.betting.entity.User;
import pl.coderslab.betting.service.*;

@Controller
public class BetController {

    @Autowired
    GameService gameService;
    @Autowired
    PlayerService playerService;
    @Autowired
    UserService userService;
    @Autowired
    BetService betService;
    @Autowired
    TeamService teamService;

    @GetMapping("/1v1bet/{id1}/{id2}")
    public String makeBet(Model model, @PathVariable Long id1, @PathVariable Long id2){
        Game game = gameService.findGameById(id1);
        if(!game.getStatus().equals("PLANNED")){
            model.addAttribute("game", game);
            return "redirect:/CantBet";
        }
        Bet bet = new Bet();
        bet.setGame(gameService.findGameById(id1));
        bet.setPlayerWhoWeBetFor(playerService.findPlayerById(id2));
        bet.setUser(userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("bet", bet);
        return "BetForm1v1";

    }
    @PostMapping("/1v1bet")
    public String makeBet(@ModelAttribute Bet bet){
        double moneySpentOnBet = bet.getMoney();
        User userWhoBets = userService.findUserById(bet.getUser().getId());
        double userMoney = userWhoBets.getMoney();
        if(userMoney<moneySpentOnBet) {
            return "redirect:/NoMoney";
        }

        double userNewMoney = userMoney-moneySpentOnBet;
        userWhoBets.setMoney(userNewMoney);
        userService.saveUserWithoutEncoding(userWhoBets);
        betService.saveBet(bet);
        return "Home";
    }

    @GetMapping("/teambet/{gameId}/{teamId}")
    public String makeTeamBet(Model model, @PathVariable Long gameId, @PathVariable Long teamId){
        Game game = gameService.findGameById(gameId);
        if(!game.getStatus().equals("PLANNED")){
            model.addAttribute("game", game);
            return "redirect:/CantBet";
        }
        Bet bet = new Bet();
        bet.setGame(gameService.findGameById(gameId));
        bet.setTeamWhichWeBetFor(teamService.findTeamById(teamId));
        bet.setUser(userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("bet", bet);
        return "BetFormTeam";

    }
    @PostMapping("/teambet")
    public String makeTeamBet(@ModelAttribute Bet bet){
        double moneySpentOnBet = bet.getMoney();
        User userWhoBets = userService.findUserById(bet.getUser().getId());
        double userMoney = userWhoBets.getMoney();
        if(userMoney<moneySpentOnBet){
            return "redirect:/NoMoney";
        }
        double userNewMoney = userMoney-moneySpentOnBet;
        userWhoBets.setMoney(userNewMoney);
        userService.saveUserWithoutEncoding(userWhoBets);
        betService.saveBet(bet);
        return "Home";
    }

    @GetMapping("/CantBet")
    public String returnCantBet(){
        return "CantBet";
    }

    @GetMapping("/NoMoney")
    public String returnNoMoney(){
        return "NoMoney";
    }

}
