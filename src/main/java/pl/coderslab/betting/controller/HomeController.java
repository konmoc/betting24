package pl.coderslab.betting.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.betting.entity.User;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showMainPage(){
        return "Home";
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
