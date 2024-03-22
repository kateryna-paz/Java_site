package com.java_site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для відображення головної сторінки.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage() {
        return "homePage";
    }
}