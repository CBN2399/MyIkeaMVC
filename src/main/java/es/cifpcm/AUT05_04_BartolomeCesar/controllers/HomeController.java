package es.cifpcm.AUT05_04_BartolomeCesar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){return "Home/index";}

    @GetMapping("/*")
    public String error(){return "Home/error";}
}
