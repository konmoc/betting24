package pl.coderslab.betting;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.coderslab.betting.service.StartAppService;


@SpringBootApplication
public class BettingOnlineApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(BettingOnlineApplication.class, args);
        context.getBean(StartAppService.class).startApp();


    }
}
