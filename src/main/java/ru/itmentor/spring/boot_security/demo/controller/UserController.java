package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String start() {
        return "index";
    }

    @GetMapping("/user")
    public String getUserProfile(Model model, Authentication authentication) {
        if (authentication != null) {
            String login = authentication.getName();
            User user = userService.findByLogin(login);
            model.addAttribute("user", user);
            return "user";
        } else {
            return "redirect:/login"; // или другая страница
        }
    }

    @GetMapping("/edit")
    public String showEditProfileForm(Model model, Authentication authentication) {
        if (authentication != null) {
            String login = authentication.getName();
            User user = userService.findByLogin(login);
            model.addAttribute("user", user);
            return "edit-user";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/edit")
    public String editProfile(@ModelAttribute("user") User updatedUser, Authentication authentication) {
        String login = authentication.getName();
        User user = userService.findByLogin(login);
        updatedUser.setId(user.getId());
        userService.saveOrUpdateUser(updatedUser);
        return "redirect:/user";
    }

}