package org.example.conrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") //Головна сторінка сайту
    public String index() {
        return "index";
    }
}
