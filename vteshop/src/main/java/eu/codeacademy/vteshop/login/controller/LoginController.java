package eu.codeacademy.vteshop.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("login-vteshop")
    public String openLoginPage() {
        return "login/login";
    }
}