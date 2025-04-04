package com.investscreener.investscreener.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<String> greet(HttpServletRequest request) {
        // Check new session for each request
        return ResponseEntity.ok("Welcome to investments screener " + request.getSession().getId());
    }

}
