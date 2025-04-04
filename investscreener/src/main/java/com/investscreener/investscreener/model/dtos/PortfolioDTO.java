package com.investscreener.investscreener.model.dtos;

import java.util.List;

public class PortfolioDTO {
//    private String portfolioName;
    private List<String> assetTickers;
    private Long userID;

    public PortfolioDTO( ) {

    }

    public PortfolioDTO(  List<String> AssetTickers, Long userID) {
        this.assetTickers = AssetTickers;
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public List<String> getAssetTickers() {
        return assetTickers;
    }

    public void setAssetTickers(List<String> assetTickers) {
        this.assetTickers = assetTickers;
    }

}
