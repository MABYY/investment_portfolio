package com.investscreener.investscreener.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.investscreener.investscreener.controller.PublicView.PublicView;
import com.investscreener.investscreener.entities.Asset;
import com.investscreener.investscreener.entities.Portfolio;
import com.investscreener.investscreener.entities.Users;
import com.investscreener.investscreener.entities.dtos.PortfolioDTO;
import com.investscreener.investscreener.entities.dtos.RequestByIdDTO;
import com.investscreener.investscreener.repositories.PortfolioRepository;
import com.investscreener.investscreener.service.AssetService;
import com.investscreener.investscreener.service.PortfolioService;
import com.investscreener.investscreener.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController()
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private AssetService assetService;

    @Autowired
    private UsersService userService ;

    @Autowired
    private PortfolioRepository portfolioRepository ;


    public PortfolioDTO convertPortfolioToDTO(Portfolio portfolio){
        PortfolioDTO portfolioDTO = new PortfolioDTO();
        Long userID = portfolio.getUsers().getId();
        List<String> assetIdsList = portfolio.getAssets().stream().map(a->a.getTicker()).toList();

        //Adds the asset Ids to the PortfolioPTO instance
        portfolioDTO.setAssetTickers(assetIdsList);

        //Adds the User Ids to the PortfolioPTO instance
        portfolioDTO.setUserID(userID);

        return portfolioDTO;
    }

    public Portfolio convertDTOToPortfolio(PortfolioDTO portfolioDTO){
        Portfolio portfolio = new Portfolio();
        Long userID = portfolioDTO.getUserID();
        Users user = userService.getUserByID(userID);
        List<Asset> assetList = portfolioDTO.getAssetTickers()
                .stream()
                .map((aLong -> assetService.findAssetByTicker(aLong)))
                .toList();

        portfolio.setUsers(user);
        portfolio.setAssets(new HashSet<>(assetList));
        portfolio.setPortfolioName(portfolioDTO.getPortfolioName());
        return portfolio;
    }


    @JsonView(PublicView.class)
    @PostMapping()
    public ResponseEntity<Portfolio> createPortfolio(@RequestBody PortfolioDTO portfolioRequest) {
        Portfolio newPortfolio = convertDTOToPortfolio(portfolioRequest);
        portfolioRepository.save(newPortfolio);
        return ResponseEntity.ok(newPortfolio);
    }


    @JsonView(PublicView.class)
    @GetMapping()
    public ResponseEntity<Portfolio> getPortfolio(@RequestBody RequestByIdDTO data) {
       return ResponseEntity.ok(portfolioService.getPortfolioByID(data.getId()));
    }

    @JsonView(PublicView.class)
    @GetMapping("/userportfolios")
    public ResponseEntity<List<Portfolio>> getPortfoliosByUser(@RequestBody RequestByIdDTO data) {
        List<Portfolio> listOfPortfolioByUserID = portfolioService.getAllPortfoliosByUserID(data.getId());
        return ResponseEntity.ok(listOfPortfolioByUserID);
    }

    @JsonView(PublicView.class)
    @PutMapping()
    public  ResponseEntity<Portfolio>  updatePortfolio(@RequestBody PortfolioDTO portfolioDTO) {
        Portfolio portfolio = convertDTOToPortfolio(portfolioDTO);
        return ResponseEntity.ok(portfolio);
    };

    @DeleteMapping()
    public ResponseEntity<Long>   deletePortfolioById(@RequestBody RequestByIdDTO data) {
        Portfolio portfolio =  portfolioService.deletePortfolio(data.getId());
        return ResponseEntity.ok(portfolio.getId());
    };


}
