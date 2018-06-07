package pl.coderslab.betting.controller;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.betting.entity.Role;
import pl.coderslab.betting.entity.User;
import pl.coderslab.betting.service.UserService;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {


    Faker faker = new Faker();

    @Autowired
    UserService userService;

    @Autowired
    Validator validator;

    @GetMapping("/add")
    @ResponseBody
    public String addUser(){
        Role role = new Role();
        role.setName("ROLE_USER");
        Set<Role> roles = new HashSet<>();

        User user = new User();
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setPassword("admin1");
        user.setMoney(0.0);
        user.setUsername(faker.name().username());
        user.setEnabled(1);
        user.setRoles(roles);
        userService.saveUser(user);
        return "Hurray!";
    }

    @GetMapping("/add/form")
    public String addUserByForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "UserAddForm";
    }

    @PostMapping("/add/form")
    public String addUserByForm(@Valid @ModelAttribute("user") User user, BindingResult result){
        if(result.hasErrors()){
            System.out.println(result.toString());
            return "UserAddForm";
        }
        user.setMoney(0.0);
        userService.saveUser(user);
        return "Success";
    }
}
