package ru.gb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
public class HomeController {
    @GetMapping("/")
    public String welcomePage(Principal principal) {
        if (Objects.isNull(principal)) {
            return "home";
        } else {
            return "redirect:/order";
        }
    }

}
