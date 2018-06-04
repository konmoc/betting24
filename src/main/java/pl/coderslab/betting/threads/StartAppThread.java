package pl.coderslab.betting.threads;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.betting.service.StartAppService;

public class StartAppThread implements Runnable {
    @Override
    public void run() {
        StartAppService startAppService = new StartAppService();
        startAppService.startApp();
    }
}
