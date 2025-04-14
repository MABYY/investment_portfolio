package com.investscreener.investscreener.service;

import com.investscreener.investscreener.entities.Portfolio;
import com.investscreener.investscreener.entities.Users;
import com.investscreener.investscreener.repositories.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UsersService usersService;

    public Portfolio addNewPortfolio(Portfolio portfolio){
        return portfolioRepository.save(portfolio);
    }

    public Portfolio getPortfolioByID(Long portfolioID){
        return portfolioRepository.getPortfolioById(portfolioID);
    }

    public List<Portfolio>  getPortfoliosByUserID(Long id){
        return usersService.getUserByID(id).getPortfolios();
    }

    public List<Portfolio> getAllPortfoliosByUserID(Long userID){
        return portfolioRepository.getAllPortfoliosByUsersID(userID);
    }

    public Portfolio addNewPortfolio (Portfolio portfolio, Users users){
        return portfolioRepository.save(portfolio);
    }

    public Portfolio deletePortfolio (Long id){
        Portfolio portfolio = portfolioRepository.getPortfolioById(id);
        portfolioRepository.deleteById(id);
        return portfolio;
    }

}
