package com.investscreener.investscreener.service;

import org.springframework.stereotype.Service;
import com.investscreener.investscreener.model.Asset;
import com.investscreener.investscreener.repo.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AssetService {
    @Autowired
    AssetRepository assetRepository ;


    public Asset saveAsset(Asset asset){
        return  assetRepository.save(asset);
    };

    public List<Asset> getAllAssets(){
        List<Asset> assets =  assetRepository.getAllAssetsQuery();
        return assets;
    };

    public  Asset findAssetByName(String name){
        Asset  assets = assetRepository.findByName(name);
        return assets;
    };

    public Asset  findAssetByTicker(String ticker){
        return assetRepository.findByTicker(ticker);
    };

    public Long deleteAssetByName(String name){
        Long  asset = assetRepository.deleteByName(name);
        return asset;
    };

    public Long deleteAssetByTicker(String ticker){
        Long  asset = assetRepository.deleteByTicker(ticker);
        return asset;
    };

    public Asset getAssetById(Long id){
        Asset  asset = assetRepository.getById(id);
        return asset;
    };
};
