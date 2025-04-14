package com.investscreener.investscreener.controller;

import com.investscreener.investscreener.entities.Users;
import com.investscreener.investscreener.repositories.UsersRepository;
import com.investscreener.investscreener.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepository usersRepository;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        return ResponseEntity.ok(
                "Welcome to investment screener " +
                usersService.register(user).getUsername());
    };

    @PostMapping("/login")
    public ResponseEntity<?> login  (@RequestBody Users user) {
        Map<String, String> response = new HashMap<String, String>();
        response.put("token", usersService.verify(user));
        return ResponseEntity.ok(response);
    };

    @GetMapping("/users/{userId}")
    public ResponseEntity<Optional<Users>> findUserData  (@PathVariable(value = "userId") Long userId) {

        return ResponseEntity.ok(usersRepository.findById(userId));
    };

}


// https://www.youtube.com/watch?v=4U0hUyktpvg

// https://stackoverflow.com/questions/61567393/concurrent-asynchronous-http-requests-in-java-and-or-spring-boot