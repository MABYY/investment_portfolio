package com.investscreener.investscreener.repositories;

import com.investscreener.investscreener.entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    Asset findByName(String name);

    Asset findByTicker(String ticker);

    @Query(value = "SELECT * FROM ASSET", nativeQuery = true)
    List<Asset> findAllAssets();
}
