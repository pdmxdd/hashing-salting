package dev.paulmatthews.hashingsalting.controllers;

import dev.paulmatthews.hashingsalting.models.User;
import dev.paulmatthews.hashingsalting.repositories.UserRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    @GetMapping
    public String getAccount(HttpSession session, Model model) {
        Optional<User> maybeUser = userRepository.findById((Long) session.getAttribute(userSessionKey));

        if(maybeUser.isEmpty()) {
            // somehow their session is all wonky so force them to logout so they need to reauthenticate
            return "redirect:/logout";
        }

        model.addAttribute("user", maybeUser.get());
        return "account";
    }
}
