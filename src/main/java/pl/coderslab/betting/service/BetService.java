package pl.coderslab.betting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.betting.entity.*;
import pl.coderslab.betting.repository.BetRepository;

import java.util.List;

@Service
public class BetService {

    @Autowired
    BetRepository betRepository;
    @Autowired
    UserService userService;
    public void saveBet(Bet bet){
        betRepository.save(bet);
    }
    public Bet findBetById(Long id){
        return betRepository.findBetById(id);
    }
    public void deleteBetById(Long id){
        betRepository.deleteBetById(id);
    }
    public List<Bet> findBetsByUser(User user){return betRepository.findBetsByUser(user);}
    public List<Bet> findBetsByGame(Game game){
        return betRepository.findAllBetsByGame(game);

    }
    public void payMoneyForBetsInGame(Game game){
        List<Bet> bets = this.findBetsByGame(game);
        for(Bet bet : bets){
            User userWhoBet = bet.getUser();
            List<Message> messages = userWhoBet.getMessagesSent();
            Message message = new Message();
            if(game.getTeamsInGame().size()>0){
                Team teamWhichWeBetFor = bet.getTeamWhichWeBetFor();
                Team teamWhichWon = game.getGameResult().getTeamWhichWon();



                if(teamWhichWeBetFor.getId()==teamWhichWon.getId()){

                    if(teamWhichWeBetFor==game.getTeamsInGame().get(0)){
                        double betMoney = bet.getMoney();
                        double odd = game.getOdd2();
                        double finalMoney = betMoney*odd;
                        userWhoBet.setMoney(finalMoney);
                    }else{
                        double betMoney = bet.getMoney();
                        double odd = game.getOdd2();
                        double finalMoney = betMoney*odd;
                        userWhoBet.setMoney(finalMoney);
                    }
                    message.setTitle("YOU WON!!!");
                    message.setContent("Hi there! You placed the right bet on the match between: "
                                                    + game.getPlayersInGame().get(0) + "and" + game.getPlayersInGame().get(1)+
                                                        ". You won " + userWhoBet.getMoney() );
                }else{
                    message.setTitle("YOU LOST!!!");
                    message.setContent("Hi there... You placed the bet on the match between " +game.getPlayersInGame());
                }

            }else{
                Player playerWhoWeBetFor = bet.getPlayerWhoWeBetFor();
                Player playerWhoWon = game.getGameResult().getPlayerWhoWon();
                if(playerWhoWeBetFor.getId()==playerWhoWon.getId()){
                    if(playerWhoWeBetFor.getId()==game.getPlayersInGame().get(0).getId()){
                        double betMoney = bet.getMoney();
                        double odd = game.getOdd2();
                        double finalMoney = betMoney*odd;
                        userWhoBet.setMoney(finalMoney);
                    }else if(playerWhoWeBetFor.getId()==game.getPlayersInGame().get(1).getId()){
                        double betMoney = bet.getMoney();
                        double odd = game.getOdd2();
                        double finalMoney = betMoney*odd;
                        userWhoBet.setMoney(finalMoney);
                    }else{
                        System.out.println("ERROR in BetService No.1 - 1v1 game");
                    }
                    message.setTitle("YOU WON!!!");
                    message.setContent("Hi there! You placed the right bet on the match between: "
                                     +game.getPlayersInGame().get(0).getNickname() + " and " + game.getPlayersInGame().get(1).getNickname()+ ". You won " + userWhoBet.getMoney() );
//                    messages.add(message);
//                    userWhoBet.setMessagesReceived(messages);

                }else {
                    message.setTitle("YOU LOST!!!");
                    message.setContent("Hi there... You placed the bet on the match between: "
                                    +game.getPlayersInGame().get(0).getNickname() + " and " +game.getPlayersInGame().get(1).getNickname() + ". You lost " + bet.getMoney() );
                }
            }
            messages.add(message);
            userWhoBet.setMessagesReceived(messages);
            userService.saveUserWithoutEncoding(userWhoBet);

        }
    }


}
