package com.investscreener.investscreener.repositories;

import com.investscreener.investscreener.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    @Query("Select p from Portfolio p where p.id = :dataId")
    Portfolio getPortfolioById(@Param("dataId") Long dataId);

    @Query("Select p from Portfolio p where p.users.id = :userID")
    List<Portfolio> getAllPortfoliosByUsersID(@Param("userID") Long userID);




}
