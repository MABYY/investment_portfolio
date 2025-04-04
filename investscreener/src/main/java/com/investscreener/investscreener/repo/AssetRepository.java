package com.investscreener.investscreener.repo;

import com.investscreener.investscreener.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    Asset findByName(String name);

    Asset findByTicker(String ticker);

    Long deleteByName(String name);

    Long deleteByTicker(String name);

    @Query("Select a from Asset a")
    List<Asset> getAllAssetsQuery();
}
