package com.investscreener.investscreener.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

//    @JsonView(Views.Public.class)
//    @Column(nullable = false)
//    private String portfolioName;

    // One portfolio can have many assets,
    // and many portfolios can have the same asset:

    @Column(nullable = false)
    private List<String> assetTickers;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "users_id")
    private Users users;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<String> getAssetTickers() {
        return assetTickers;
    }

    public void setAssetTickers (List<String> assetTickers) {
        this.assetTickers = assetTickers;
    }

}
