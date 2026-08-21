package org.example.conrollers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.example.dtos.account.RegisterDto;
import org.example.services.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "account/register";
    }

    @PostMapping("/register")
    public String register(RegisterDto dto, Model model, HttpServletRequest request) {
        try {
            accountService.register(dto, request);
            return "redirect:/";
        }
        catch (Exception e) {
            model.addAttribute("registerDto", dto);
            model.addAttribute("error", e.getMessage());
            return "account/register";
        }
    }
}
