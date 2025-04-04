package com.investscreener.investscreener.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.investscreener.investscreener.controller.PublicView.PublicView;
import com.investscreener.investscreener.model.Asset;
import com.investscreener.investscreener.model.dtos.RequestAssetNameDTO;
import com.investscreener.investscreener.model.dtos.RequestAssetTickerDTO;
import com.investscreener.investscreener.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;


    @GetMapping()
    public ResponseEntity<List<Asset> > getAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @JsonView(PublicView.class)
    @PostMapping("/add")
    public ResponseEntity<Asset> addNewAsset(@RequestBody Asset asset) {
        return ResponseEntity.ok(asset);
    }

    @JsonView(PublicView.class)
    @GetMapping("/findbyname")
    public ResponseEntity<Asset> getAssetByName(@RequestBody RequestAssetNameDTO name) {
        return ResponseEntity.ok(assetService.findAssetByName(name.getName()));
    };

    @JsonView(PublicView.class)
    @GetMapping("/findbyticker")
    public ResponseEntity<Asset>  getAssetByTicker(@RequestBody RequestAssetTickerDTO ticker) {
        return ResponseEntity.ok(assetService.findAssetByTicker(ticker.getTicker()));
    };

    @PutMapping("/detetebyname")
    public ResponseEntity<Long> deleteAssetByName(@RequestBody RequestAssetNameDTO name) {
        return ResponseEntity.ok(assetService.deleteAssetByName(name.getName()));
    };

    @PutMapping("/deletebyticker")
    public ResponseEntity<Long>  deleteAssetByTicker(@RequestBody RequestAssetTickerDTO ticker) {
        return ResponseEntity.ok(assetService.deleteAssetByTicker(ticker.getTicker()));
    };


    // HTTP Client
    // https://www.youtube.com/watch?v=aR580OCEp7w

    // Response Entity
    // https://www.youtube.com/watch?v=B5Zrn1Tzyqw

}
