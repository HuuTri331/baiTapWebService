package com.codegym.c0324h1_spring_boot_2.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String showPageLogin(@CookieValue(value = "username", defaultValue = "")String username,
                                @CookieValue(value = "password", defaultValue = "") String password,
                                Model model) {
        System.out.println(username);
        System.out.println(password);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "security/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        @RequestParam(value = "remember-me", defaultValue = "false")Boolean remember,
                        HttpServletResponse response) {
//        Check đăng nhập
//        mặc định là đăng nhập thành công
        if(remember) {
            Cookie cookieUsername = new Cookie("username", username);
            cookieUsername.setHttpOnly(true);
            cookieUsername.setMaxAge(60*60*24);
            response.addCookie(cookieUsername);
            Cookie cookiePassword = new Cookie("password", password);
            cookiePassword.setHttpOnly(true);
            cookieUsername.setMaxAge(60*60*24);
            response.addCookie(cookiePassword);
        } else {

        }
        return "redirect:/student";
    }

}
