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

    /**
     * This is one of the main methods in this application. It is a part of Game Service method - (setGameStatusesSetWinnerAndPayMoney)
     * If game is finished then all of its bets are solved. We pay money to the winners and send messages to all of Users who placed bets.
     * @param game
     */
    public void payMoneyForBetsInGame(Game game){
        List<Bet> bets = this.findBetsByGame(game);
        for(Bet bet : bets){
            User userWhoBet = bet.getUser();
            List<Message> messages = userWhoBet.getMessagesSent();
            double finalMoney = 0.0;
            double betMoney = 0.0;
            Message message = new Message();
            if(game.getTeamsInGame().size()>0){
                Team teamWhichWeBetFor = bet.getTeamWhichWeBetFor();
                Team teamWhichWon = game.getGameResult().getTeamWhichWon();

                if(teamWhichWeBetFor.getId()==teamWhichWon.getId()){

                    if(teamWhichWeBetFor.getId()==game.getTeamsInGame().get(0).getId()){
                        betMoney = bet.getMoney();
                        double odd = game.getOdd2();
                        finalMoney = betMoney*odd;
                        userWhoBet.setMoney(userWhoBet.getMoney()+finalMoney);
                    }else if(teamWhichWeBetFor.getId()==game.getTeamsInGame().get(1).getId()){
                        betMoney = bet.getMoney();
                        double odd = game.getOdd2();
                        finalMoney = betMoney*odd;
                        userWhoBet.setMoney(userWhoBet.getMoney()+finalMoney);
                    }else{
                        System.out.println("ERROR in BetService No.1 - team game");
                    }
                    message.setTitle("YOU WON!!!");
                    message.setContent("Hi there! You placed the right bet on the match between: "
                                                    + game.getTeamsInGame().get(0).getName() + " and " + game.getTeamsInGame().get(1).getName()+
                                                        ". You won " + finalMoney );
                }else{
                    message.setTitle("YOU LOST...");
                    message.setContent("Hi there... You placed the bet on the match between: "
                            +game.getTeamsInGame().get(0).getName() + " and " +game.getTeamsInGame().get(1).getName() + ". You lost " + bet.getMoney() );
                }

            }else{
                Player playerWhoWeBetFor = bet.getPlayerWhoWeBetFor();
                Player playerWhoWon = game.getGameResult().getPlayerWhoWon();
                if(playerWhoWeBetFor.getId()==playerWhoWon.getId()){
                    if(playerWhoWeBetFor.getId()==game.getPlayersInGame().get(0).getId()){
                        betMoney = bet.getMoney();
                        double odd = game.getOdd2();
                        finalMoney = betMoney*odd;
                        userWhoBet.setMoney(userWhoBet.getMoney()+finalMoney);
                    }else if(playerWhoWeBetFor.getId()==game.getPlayersInGame().get(1).getId()){
                        betMoney = bet.getMoney();
                        double odd = game.getOdd2();
                        finalMoney = betMoney*odd;
                        userWhoBet.setMoney(userWhoBet.getMoney()+finalMoney);
                    }else{
                        System.out.println("ERROR in BetService No.1 - 1v1 game");
                    }
                    message.setTitle("YOU WON!!!");
                    message.setContent("Hi there! You placed the right bet on the match between: "
                                     +game.getPlayersInGame().get(0).getNickname() + " and " + game.getPlayersInGame().get(1).getNickname()+ ". You won " + finalMoney );

                }else {
                    message.setTitle("YOU LOST...");
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
