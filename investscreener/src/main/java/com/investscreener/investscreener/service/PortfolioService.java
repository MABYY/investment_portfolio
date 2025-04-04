package com.investscreener.investscreener.service;

import com.investscreener.investscreener.model.Portfolio;
import com.investscreener.investscreener.model.Users;
import com.investscreener.investscreener.repo.PortfolioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepo portfolioRepo;

    @Autowired
    private UsersService usersService;

    public Portfolio addNewPortfolio(Portfolio portfolio){
        System.out.println("PORTFOLIOooooooo" + portfolio);
        return portfolioRepo.save(portfolio);
    }

    public Portfolio getPortfolioByID(Long portfolioID){
        return portfolioRepo.getPortfolioById(portfolioID);
    }

    public List<Portfolio>  getPortfoliosByUserID(Long id){
        return usersService.getUserByID(id).getPortfolios();
    }

//    public List<Portfolio> getAllPortfoliosByUserID(Long userID){
//        List<Portfolio> portfolios = portfolioRepo.getAllPortfoliosByUsersID(userID);
//        return portfolios;
//    }

    public Portfolio addNewPortfolio (Portfolio portfolio, Users users){
        Portfolio savedPortfolio = portfolioRepo.save(portfolio);

        return savedPortfolio;
    }

//    public Portfolio editPortfolio (Portfolio portfolio, Users users){
//        Long id = portfolio.getId();
//
//        Portfolio savedPortfolio = portfolioRepo.getPortfolioById(id);
//
//        savedPortfolio.setAssets(portfolio.getAssets());
//
//        return portfolioRepo.getPortfolioById(id);
//    }
//
//    public Portfolio deletePortfolio (Long id){
//
//        portfolioRepo.deleteById(id);
//
//        return portfolioRepo.getPortfolioById(id);
//    }

}
