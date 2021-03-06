package dev.paulmatthews.hashingsalting.controllers;

import dev.paulmatthews.hashingsalting.models.LoginUserDTO;
import dev.paulmatthews.hashingsalting.models.RegisterUserDTO;
import dev.paulmatthews.hashingsalting.models.User;
import dev.paulmatthews.hashingsalting.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping
    public String getIndex() {
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String getLoginForm() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String postLoginForm(@ModelAttribute LoginUserDTO loginUserDTO, Model model) {
        // check our DB for the loginUser:
        Optional<User> maybeUser = userRepository.findByUserName(loginUserDTO.getUsername());
        if(maybeUser.isEmpty()) {
            model.addAttribute("error", "Username and/or password are incorrect");
            return "login";
        }
        // check the password hash of the loginUserDTO and the User
        User user = maybeUser.get();
        if(!encoder.matches(loginUserDTO.getPassword(), user.getPasswordHash())) {
            model.addAttribute("error", "Username and/or password are incorrect");
            return "login";
        }
        // finally if the user exists in the DB AND the password hashes match let the user in:
        model.addAttribute("user", user);
        return "login-successful";
    }

    @GetMapping(value = "/register")
    public String getRegisterForm() {
        return "sign-up";
    }

    @PostMapping(value = "/register")
    public String postRegisterForm(@ModelAttribute RegisterUserDTO registerUserDTO, Model model) {
        // check that the user doesn't already exist in the database:
        Optional<User> maybeUser = userRepository.findByUserName(registerUserDTO.getUsername());
        if(maybeUser.isPresent()) {
            model.addAttribute("error", "Username already registered");
            return "sign-up";
        }
        // check that the passwords match:
        if(!registerUserDTO.confirmPasswords()) {
            model.addAttribute("error", "Passwords do not match");
            return "sign-up";
        }
        // finally if passwords match and username isn't already registered create the user, save it to the DB and return a successful registration page
        User newUser = User.fromRegisterUserDTO(registerUserDTO);
        userRepository.save(newUser);
        model.addAttribute("user", newUser);
        return "sign-up-successful";
    }
}
