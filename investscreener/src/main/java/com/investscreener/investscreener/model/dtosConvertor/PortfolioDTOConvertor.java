//package com.investscreener.investscreener.model.dtosConvertor;
//
//import com.investscreener.investscreener.model.Asset;
//import com.investscreener.investscreener.model.Portfolio;
//import com.investscreener.investscreener.model.Users;
//import com.investscreener.investscreener.model.dtos.PortfolioDTO;
//import com.investscreener.investscreener.service.AssetService;
//import com.investscreener.investscreener.service.UsersService;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//
//import java.util.List;
//
//@Bean
//public class PortfolioDTOConvertor {
//
//    @Autowired
//    private UsersService userService ;
//
//    @Autowired
//    private AssetService assetService;
//
//    public PortfolioDTO convertPortfolioToDTO(Portfolio portfolio){
//        PortfolioDTO portfolioDTO = new PortfolioDTO();
//        Long userID = portfolio.getUsers().getId();
//        List<String> assetIdsList = portfolio.getAssets().stream()
//                .map(Asset::getTicker)
//                .toList();
//
//        // BeanUtils.copyProperties(portfolio, portfolioDTO, "userID", "AssetIDs");
//
//        //Adds the asset Ids to the PortfolioPTO instance
//        portfolioDTO.setAssetTickers(assetIdsList);
//
//        //Adds the User Ids to the PortfolioPTO instance
//        portfolioDTO.setUserID(userID);
//
//        return portfolioDTO;
//    }
//
//
//    public Portfolio convertDTOToPortfolio(PortfolioDTO portfolioDTO){
//        Portfolio portfolio = new Portfolio();
//        Long userID = portfolioDTO.getUserID();
//        Users user = userService.getUserByID(userID);
//        List<Asset> assetList = portfolioDTO.getAssetTickers()
//                .stream()
//                .map((aLong -> assetService.findAssetByTicker(aLong)))
//                .toList();
//
//        // BeanUtils.copyProperties(portfolioDTO, portfolio);
//
//        //Adds the User to the Portfolio instance
//
//        portfolio.setUsers(user);
//
//        portfolio.setAssets(assetList);
//
//        return portfolio;
//    }
//
//
//}
