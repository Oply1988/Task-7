package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorUserController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(@RequestParam(required = false) String errorMessage, Model model) {
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "user-error";
    }
}
