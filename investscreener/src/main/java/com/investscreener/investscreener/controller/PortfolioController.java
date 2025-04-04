package com.investscreener.investscreener.controller;

import com.investscreener.investscreener.model.Asset;
import com.investscreener.investscreener.model.Portfolio;
import com.investscreener.investscreener.model.Users;
import com.investscreener.investscreener.model.dtos.PortfolioDTO;
import com.investscreener.investscreener.repo.PortfolioRepo;
import com.investscreener.investscreener.repo.UsersRepo;
import com.investscreener.investscreener.service.AssetService;
import com.investscreener.investscreener.service.PortfolioService;
import com.investscreener.investscreener.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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
    private PortfolioRepo portfolioRepository ;


    public PortfolioDTO convertPortfolioToDTO(Portfolio portfolio){
        PortfolioDTO portfolioDTO = new PortfolioDTO();
        Long userID = portfolio.getUsers().getId();
        List<String> assetIdsList = portfolio.getAssetTickers();

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
//        portfolio.setAssetsId(assetList);
        return portfolio;
    }


    @PostMapping("/{userId}")
    public ResponseEntity<PortfolioDTO> createPortfolio(@PathVariable(value = "userId") Long userId,
                                          @RequestBody Portfolio portfolioRequest) {

        Users user = userService.getUserByID(userId);
        portfolioRequest.setUsers(user);
        return ResponseEntity.ok(
                        convertPortfolioToDTO(portfolioRepository.save(portfolioRequest))
                );
    }

    @GetMapping()
    public ResponseEntity<PortfolioDTO> getPortfolio(@RequestBody Long id) {
       Portfolio portfolio = portfolioService.getPortfolioByID(id);
       return ResponseEntity.ok(convertPortfolioToDTO(portfolio));
    }

    @GetMapping("/userportfolios")
    public ResponseEntity<List<PortfolioDTO>> getPortfoliosByUser(@RequestBody Long userID) {
        System.out.println("userID !!!!!! " + userID);
        List<Portfolio> listOfPortfolioByUserID = portfolioService.getPortfoliosByUserID(userID);

        return ResponseEntity.ok(
                listOfPortfolioByUserID.stream()
                        .map(this::convertPortfolioToDTO)
                        .collect(toList())
        );
    }


//    @PutMapping()
//    public Long updatePortfolio(@RequestBody PortfolioDTO portfolioDTO) {
//        Portfolio portfolio = convertDTOToPortfolio(portfolioDTO);
//        Users user = userService.getUserByID(portfolioDTO.getUserID());

//        portfolio.setUsers(portEdit.getUsers());
//        portfolio.setAssets(portEdit.getAssets());

//        return portfolio.getId();
//    };








}
