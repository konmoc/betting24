package pl.coderslab.betting.service;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.betting.entity.Game;
import pl.coderslab.betting.entity.GameResult;
import pl.coderslab.betting.entity.Player;
import pl.coderslab.betting.entity.Team;
import pl.coderslab.betting.repository.GameRepository;
import pl.coderslab.betting.repository.GameResultRepository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerService playerService;
    @Autowired
    VideoGameService videoGameService;
    @Autowired
    BetService betService;
    @Autowired
    GameResultService gameResultService;
    @Autowired
    TeamService teamService;

    Random random = new Random();

    public void saveGame(Game game){
        gameRepository.save(game);
    }

    public Game findGameById(Long id){
        return gameRepository.findGameById(id);
    }

    public void deleteGameWithId(Long id){
        gameRepository.deleteGameById(id);
    }


    @Scheduled(fixedDelay = 1000)
    public void create1v1Games(){

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Game game = new Game();
        //start of the game:
        LocalDateTime timeToGame = LocalDateTime.now()
                                                .plusDays( 0 )
                                                .plusHours( 0 )
                                                .plusMinutes( 3 );
        game.setDateAndTime(timeToGame);
        game.setStatus("PLANNED");
        double odd1 = ThreadLocalRandom.current().nextDouble(1, 3);
        double odd2 = ThreadLocalRandom.current().nextDouble(1, 3);
        game.setOdd1(Precision.round(odd1,2));
        game.setOdd2(Precision.round(odd2,2));

        //two players taking part in the game:
        Player player1 = playerService.findPlayerById((long) random.nextInt(100) + 1);
        Player player2 = playerService.findPlayerById((long) random.nextInt(100) + 1);

        while(player1==player2){
            player2=playerService.findPlayerById((long) random.nextInt(100) + 1);
        }

        List<Player> playersInGame = Arrays.asList(player1,player2);
        game.setPlayersInGame(playersInGame);
        game.setVideoGame(videoGameService.findVideoGameById((long) random.nextInt(6) + 1));


        this.saveGame(game);

        System.out.println("1v1 GAME CREATED AND SAVED TO DATABASE");

    }
    @Scheduled(fixedDelay = 30000)
    public void createTeamGames(){
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Game game = new Game();
        //start of the game:
        LocalDateTime timeToGame = LocalDateTime.now()
                                                .plusDays( 0 )
                                                .plusHours( 0 )
                                                .plusMinutes( 3 );
        game.setDateAndTime(timeToGame);
        game.setStatus("PLANNED");

        double odd1 = ThreadLocalRandom.current().nextDouble(1, 3);
        double odd2 = ThreadLocalRandom.current().nextDouble(1, 3);
        game.setOdd1(Precision.round(odd1,2));
        game.setOdd2(Precision.round(odd2,2));

        //two teams taking part in the game:
        Team team1 = teamService.findTeamById((long) random.nextInt(20) + 1);
        Team team2 = teamService.findTeamById((long) random.nextInt(20) + 1);
        while (team1==team2 && team1.getVideoGame()==team2.getVideoGame()){
            team2 = teamService.findTeamById((long) random.nextInt(20) + 1);
        }
        List<Team> teamsInGame = Arrays.asList(team1,team2);
        game.setTeamsInGame(teamsInGame);
        game.setVideoGame(team1.getVideoGame());
        this.saveGame(game);

        System.out.println("TEAM GAME CREATED AND SAVED TO DATABASE");

    }


    public List<Game> findAll1v1Games(){
        return gameRepository.findGamesByPlayersInGameIsNotNull();
    }

    public List<Game> findAllGames(){
        return gameRepository.findAll();
    };

    public List<Game> findAllTeamGames(){
        return gameRepository.findGamesByTeamsInGameIsNotNull();
    }

    List<Game> findGamesByPlayersInGameContaining(Player player){
         return gameRepository.findGamesByPlayersInGameContaining(player);
    }

    public double getWinRatio(Game game){

        if(game.getPlayersInGame() != null) {
            return game.getPlayersInGame().get(0).getWinRatio() - game.getPlayersInGame().get(1).getWinRatio() + 50;
        }else{
            return game.getTeamsInGame().get(0).getWinRatio() - game.getTeamsInGame().get(1).getWinRatio() + 50;
        }

    }

    public List<Game> findAllGamesByPlayer(Player player){
        List<Game> allGames = this.findAllGames();
        List<Game> gamesContainingPlayer = new ArrayList<>();
        for(Game game : allGames){
            if(game.getPlayersInGame().contains(player)){
                gamesContainingPlayer.add(game);
            }
        }
        return gamesContainingPlayer;
    }

//    @Scheduled(fixedDelay = 40000)
//    public void setGameStatusesSetWinnerAndPayMoney1v1(){
//        List<Game> allGames = this.findAll1v1Games();
//            for (Game game: allGames) {
//
//                LocalDateTime gameStarts = game.getDateAndTime();
//                LocalDateTime gameEnds = gameStarts.plusMinutes(game.getVideoGame().getRoundDuration()*game.getVideoGame().getNumberOfRounds());
//                if(gameStarts.isBefore(LocalDateTime.now())){
//                    if(gameEnds.isAfter(LocalDateTime.now())){
//                        game.setStatus("STARTED");
//                        this.saveGame(game);
//                    }else if(game.getGameResult()==null){
//                        game.setStatus("FINISHED");
//                        //we choose winer randomly:
//                        GameResult result = new GameResult();
//                        List<Player> players = game.getPlayersInGame();
//                        Player playerWhoWon = players.get(random.nextInt(1));
//
//                        result.setPlayerWhoWon(playerWhoWon);
//                        result.setStatus("WON by " + playerWhoWon.getNickname() );
//                        gameResultService.saveGameResult(result);
//                        game.setGameResult(result);
//                        this.saveGame(game);
//                        //we pay users money for their bets:
//                        betService.payMoneyForBetsInGame(game);
//                    }
//                    this.saveGame(game);
//                }
//
//
//            }
//        System.out.println("GAME STATUSES FIXED AND MONEY FOR BETS PAYED");
//
//
//        }

    @Scheduled(fixedDelay = 8000)
    public void setGameStatusesSetWinnerAndPayMoney(){
        List<Game> allGames = this.findAllGames();
        for (Game game: allGames) {

            LocalDateTime gameStarts = game.getDateAndTime();
            LocalDateTime gameEnds = gameStarts.plusSeconds(game.getVideoGame().getRoundDuration()*game.getVideoGame().getNumberOfRounds());
            if(gameStarts.isBefore(LocalDateTime.now())){
                if(gameEnds.isAfter(LocalDateTime.now())){
                    game.setStatus("STARTED");
                    this.saveGame(game);
                }else if(!game.getStatus().equals("FINISHED")){
                    game.setStatus("FINISHED");
                    GameResult result = new GameResult();
                    if(game.getTeamsInGame().size()>0){
                        //we choose winner randomly:
                        List<Team> teams = game.getTeamsInGame();
                        Team teamWhichWon = teams.get(random.nextInt(1));

                        result.setTeamWhichWon(teamWhichWon);
                        result.setStatus("WON by " + teamWhichWon.getName());
                        gameResultService.saveGameResult(result);
                        this.saveGame(game);

                    }else{
                        //we choose winner randomly:

                        List<Player> players = game.getPlayersInGame();
                        Player playerWhoWon = players.get(random.nextInt(2));

                        result.setPlayerWhoWon(playerWhoWon);
                        result.setStatus("WON by " + playerWhoWon.getNickname());
                        gameResultService.saveGameResult(result);
                        game.setGameResult(result);
                        this.saveGame(game);
                    }
                    //we pay users money for their bets:
                    betService.payMoneyForBetsInGame(game);

                }
                this.saveGame(game);
            }


        }
        System.out.println("GAME STATUSES FIXED AND MONEY FOR BETS PAYED");


    }


}
