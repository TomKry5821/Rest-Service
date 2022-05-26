package com.krypczyk.restservice.controller;

import com.krypczyk.restservice.Exception.UnauthorizedException;
import com.krypczyk.restservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        return "Your access Token - " + this.loginService.login(username, password);
    }

    @PostMapping("/logout")
    public String logout(@RequestHeader("accessToken") String accessToken) {
        this.loginService.logout(accessToken);
        return "Logout successfully";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String handleException(UnauthorizedException unauthorizedException) {
        return unauthorizedException.getMessage();
    }
}
