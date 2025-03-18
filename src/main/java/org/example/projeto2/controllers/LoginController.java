package org.example.projeto2.controllers;

import org.example.projeto2.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/{nome}/{password}")
    public String login(@PathVariable String nome, @PathVariable String password) {
        boolean success = loginService.login(nome, password);
        return success ? "Login bem-sucedido!" : "Falha no login.";
    }
}
