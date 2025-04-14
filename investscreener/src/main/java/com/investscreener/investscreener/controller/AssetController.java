package com.investscreener.investscreener.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.investscreener.investscreener.controller.PublicView.PublicView;
import com.investscreener.investscreener.entities.Asset;
import com.investscreener.investscreener.entities.dtos.RequestAssetNameDTO;
import com.investscreener.investscreener.entities.dtos.RequestAssetTickerDTO;
import com.investscreener.investscreener.entities.dtos.RequestByIdDTO;
import com.investscreener.investscreener.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;


    @JsonView(PublicView.class)
    @GetMapping()
    public ResponseEntity<List<Asset> > getAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @JsonView(PublicView.class)
    @PostMapping("/add")
    public ResponseEntity<Asset> addNewAsset(@RequestBody Asset asset) {
        assetService.saveAsset(asset);
        return ResponseEntity.ok(asset);
    }

    @JsonView(PublicView.class)
    @GetMapping("/findbyname")
    public ResponseEntity<Asset> getAssetByName(@RequestBody RequestAssetNameDTO name) {
        return ResponseEntity.ok(assetService.findAssetByName(name.getName()));
    };

    @JsonView(PublicView.class)
    @GetMapping("/findbyId")
    public ResponseEntity<Asset>  getAssetByTicker(@RequestBody RequestByIdDTO data) {
        return ResponseEntity.ok(assetService.getAssetById(data.getId()));
    }

    @JsonView(PublicView.class)
    @GetMapping("/findbyticker")
    public ResponseEntity<Asset>  getAssetByTicker(@RequestBody RequestAssetTickerDTO ticker) {
        return ResponseEntity.ok(assetService.findAssetByTicker(ticker.getTicker()));
    };

    @DeleteMapping()
    public void deleteAssetById(@RequestBody RequestByIdDTO request) {
        assetService.deleteAssetById(request.getId());
    };


    // HTTP Client
    // https://www.youtube.com/watch?v=aR580OCEp7w

    // Response Entity
    // https://www.youtube.com/watch?v=B5Zrn1Tzyqw

}
