package es.cifpcm.AUT05_04_BartolomeCesar.controllers;

import es.cifpcm.AUT05_04_BartolomeCesar.models.User;
import es.cifpcm.AUT05_04_BartolomeCesar.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        return "/Auth/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "/Auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult br){

        if(br.hasErrors()){
            return "/Auth/register";
        }
        userService.createUser(user);
        return "redirect:/";
    }

    @GetMapping("/denied")
    public String denegar(){
        return "/Auth/denied";
    }
}
