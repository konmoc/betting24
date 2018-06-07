package pl.coderslab.betting.service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.betting.entity.*;

import java.util.*;

@Service
public class StartAppService {

    @Autowired
    RoleService roleService;
    @Autowired
    PlayerService playerService;
    @Autowired
    GenreService genreService;
    @Autowired
    VideoGameService videoGameService;
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;

    Faker faker = new Faker();

    Random random = new Random();

    public void startApp() {

        if(playerService.countAllPlayers() == 0){
        //1)we create roles - both for admin and user:

        Role userRole = new Role();
        Role adminRole = new Role();
        userRole.setName("USER");
        adminRole.setName("ADMIN");
        roleService.saveRole(userRole);
        roleService.saveRole(adminRole);

        //2)we create game genres:

        //FPS:
        Genre fpsGenre = new Genre();
        fpsGenre.setName("FPS");
        genreService.saveGenre(fpsGenre);
        //RTS:
        Genre rtsGenre = new Genre();
        rtsGenre.setName("RTS");
        genreService.saveGenre(rtsGenre);
        //STRATEGY:
        Genre strategyGenre = new Genre();
        strategyGenre.setName("STRATEGY");
        genreService.saveGenre(strategyGenre);
        //MOBA:
        Genre mobaGenre = new Genre();
        mobaGenre.setName("MOBA");
        genreService.saveGenre(mobaGenre);

        //3)we create video games:

        //Battlefield 1:
        VideoGame battlefield = new VideoGame();
        battlefield.setGenre(genreService.findGenreByName("FPS"));
        battlefield.setName("Battlefield 1");
        battlefield.setRoundDuration(40);
        battlefield.setNumberOfRounds(new Long(1));
        battlefield.setMaxAmountOfPlayersInTeam(new Long(20));
        videoGameService.saveVideoGame(battlefield);

        //Counter Strike: Global Offensive:
        VideoGame counterStrike = new VideoGame();
        counterStrike.setGenre(genreService.findGenreByName("FPS"));
        counterStrike.setName("Counter-Strike: Global Offensive");
        counterStrike.setRoundDuration(30);
        counterStrike.setNumberOfRounds(new Long(3));
        counterStrike.setMaxAmountOfPlayersInTeam(new Long(10));
        videoGameService.saveVideoGame(counterStrike);

        //Company Of Heroes 2:
        VideoGame CoH = new VideoGame();
        CoH.setGenre(genreService.findGenreByName("RTS"));
        CoH.setName("Company of Heroes 2");
        CoH.setRoundDuration(50);
        CoH.setNumberOfRounds(new Long(1));
        CoH.setMaxAmountOfPlayersInTeam(new Long(8));
        videoGameService.saveVideoGame(CoH);

        //StarCraft 3:
        VideoGame starCraft = new VideoGame();
        starCraft.setGenre(genreService.findGenreByName("RTS"));
        starCraft.setName("StarCraft 3");
        starCraft.setRoundDuration(50);
        starCraft.setNumberOfRounds(new Long(1));
        starCraft.setMaxAmountOfPlayersInTeam(new Long(4));
        videoGameService.saveVideoGame(starCraft);

        //AGE OF EMPIRES 2:
        VideoGame AoE = new VideoGame();
        AoE.setGenre(genreService.findGenreByName("STRATEGY"));
        AoE.setName("Age of Empires 2");
        AoE.setRoundDuration(60);
        AoE.setNumberOfRounds(new Long(1));
        AoE.setMaxAmountOfPlayersInTeam(new Long(2));
        videoGameService.saveVideoGame(AoE);

        //Total War: Warhammer:
        VideoGame TWarhammer = new VideoGame();
        TWarhammer.setGenre(genreService.findGenreByName("STRATEGY"));
        TWarhammer.setName("Age of Empires 2");
        TWarhammer.setRoundDuration(60);
        TWarhammer.setNumberOfRounds(new Long(1));
        TWarhammer.setMaxAmountOfPlayersInTeam(new Long(2));
        videoGameService.saveVideoGame(TWarhammer);

        //League of Legends:
        VideoGame LOL = new VideoGame();
        LOL.setGenre(genreService.findGenreByName("MOBA"));
        LOL.setName("League of Legends");
        LOL.setRoundDuration(70);
        LOL.setNumberOfRounds(new Long(1));
        LOL.setMaxAmountOfPlayersInTeam(new Long(5));
        videoGameService.saveVideoGame(LOL);

        //Dota 2:
        VideoGame dota2 = new VideoGame();
        dota2.setGenre(genreService.findGenreByName("MOBA"));
        dota2.setName("Dota 2");
        dota2.setRoundDuration(50);
        dota2.setNumberOfRounds(new Long(1));
        dota2.setMaxAmountOfPlayersInTeam(new Long(5));
        videoGameService.saveVideoGame(dota2);

        //we create 100 starting players:
        for (int i = 0; i < 100; i++) {
            Player player = new Player();
            player.setNickname(faker.name().username());
            player.setFirstName(faker.name().firstName());
            player.setLastName(faker.name().lastName());
            player.setWinRatio(100.0);
//            player.setPlayerTeams();
            playerService.savePlayer(player);
        }

        //we create 20 starting teams:
        for (int j = 0; j < 20; j++) {
            Team team = new Team();

            team.setName(faker.beer().name());

            VideoGame videoGame = videoGameService.findVideoGameById((long) random.nextInt(6) + 1);
            Long maxNumOfPlayers = videoGame.getMaxAmountOfPlayersInTeam();

            team.setVideoGame(videoGame);

            List<Player> playerList = new ArrayList<>();
            for (int a = 0; a < maxNumOfPlayers; a++) {
                Player player = playerService.findPlayerById((long) random.nextInt(100) + 1);
                while (playerList.contains(player)) {
                    player = playerService.findPlayerById((long) random.nextInt(100) + 1);
                }
                playerList.add(player);
            }
            team.setPlayerList(playerList);
            team.setWinRatio(100.0);
            teamService.saveTeam(team);

        }

            //we create 5 users
            for(int i=0;i<100;i++){
                User user = new User();
                user.setUsername("userNo"+i);
                user.setFirstName(faker.name().firstName());
                user.setLastName(faker.name().lastName());
                user.setPassword("user");
                Set<Role> roles = new HashSet<>();
                roles.add(roleService.findRoleById(1L));
                user.setRoles(roles);
                user.setMoney(1000.0);
                userService.saveUser(user);

            }
    }else{
            System.out.println("STARTING DATA ALREADY INITIALIZED");
        }
    }

}
