package ru.alex.myvote.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:voting";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String getUsers() {
        return "users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/restaurants")
    public String getRestaurantsToAdmin() {
        return "aRestaurants";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/voting")
    public String getRestaurants() {
        return "restaurants";
    }

    @GetMapping("/history")
    public String getHistory() {
        return "history";
    }
}
