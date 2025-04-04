package com.investscreener.investscreener.service;

import com.investscreener.investscreener.model.Portfolio;
import com.investscreener.investscreener.model.Users;
import com.investscreener.investscreener.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    public String verify(Users user) {
        // Genertate auth object
        System.out.println("TRIAL");
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        // Chech the user exists and generate jwt if corresponds
        System.out.println("authentication.isAuthenticated() " + authentication.isAuthenticated());
        if (authentication.isAuthenticated()) {
            // If authenticated return jwt
            System.out.println( " USER FOUND");
            return jwtService.generateToken(user.getUsername());
        } else {
            System.out.println( " USER NOT FOUND");
            return "User does not exist";
        }
    };

    public void addPortfolioToUser(Portfolio portfolio, Users user){

        List<Portfolio> listOfUserPortfolios = user.getPortfolios();

        if(listOfUserPortfolios == null){
            listOfUserPortfolios = new ArrayList<>();
        }

        listOfUserPortfolios.add(portfolio);

        user.setPortfolios(listOfUserPortfolios);
        repo.save(user);

    }

    public Users getUserByID(Long id) {
        return repo.getReferenceById(id);
    }
}
