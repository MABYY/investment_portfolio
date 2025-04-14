package com.investscreener.investscreener.service;

import org.springframework.stereotype.Service;
import com.investscreener.investscreener.entities.Asset;
import com.investscreener.investscreener.repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    @Autowired
    AssetRepository assetRepository ;

    public void saveAsset(Asset asset){
        assetRepository.save(asset);
    };

    public List<Asset> getAllAssets(){
        List<Asset> assets =  assetRepository.findAllAssets();
        return assets;
    };


    public  Asset findAssetByName(String name){
        Asset  assets = assetRepository.findByName(name);
        return assets;
    };

    public Asset  findAssetByTicker(String ticker){
        return assetRepository.findByTicker(ticker);
    };


    public void deleteAssetById(Long id){
        Optional<Asset>  asset = assetRepository.findById(id);
        System.out.println("ASSET FOUND " + asset);
        assetRepository.deleteById(id);
    }

    public Asset getAssetById(Long id){
        Asset  asset = assetRepository.getById(id);
        return asset;
    };
};
