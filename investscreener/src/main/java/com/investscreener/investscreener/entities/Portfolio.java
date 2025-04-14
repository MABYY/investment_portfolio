package com.investscreener.investscreener.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.investscreener.investscreener.controller.PublicView.PublicView;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @JsonView(PublicView.class)
    private Long id;

    @JsonView(PublicView.class)
    @Column(nullable = false)
    private String portfolioName;

    @JsonView(PublicView.class)
    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable( // create the FK
            name = "port_asset",
            joinColumns = @JoinColumn(name="portfolio_id"),
            inverseJoinColumns = @JoinColumn(name="asset_id"))
    private Set<Asset> assets = new HashSet<>();

//    private Set<String> assets_LIST = new HashSet<>();

    @ManyToOne(
            fetch = FetchType.EAGER// fetch strategy only impacts the entity owner

    )
    @JoinColumn(name = "users_id") // reference the FK
    private Users users;



}
