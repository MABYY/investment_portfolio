package com.investscreener.investscreener.model;
import com.fasterxml.jackson.annotation.JsonView;
import com.investscreener.investscreener.controller.PublicView.PublicView;
import com.investscreener.investscreener.model.enums.AssetType;
import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @JsonView(PublicView.class)
    @Column(unique = true, nullable = false)
    private String isin;

    @JsonView(PublicView.class)
    @Column( unique = true, nullable = false)
    private String ticker;

    @JsonView(PublicView.class)
    @Column( length = 100, nullable = false)
    private String name;

    @JsonView(PublicView.class)
    @Column( nullable = false)
    @Enumerated(EnumType.STRING)
    private AssetType type;

//    @ManyToMany( mappedBy = "assets",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST ) //many assets can belong to one portfolio
//    private List<Portfolio> portfolios;




//    public Asset( String isin, String ticker, String name, AssetType type ) {
////        this.isin = isin;
//        this.ticker = ticker;
//        this.name = name;
//        this.type = type;
//    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
        this.type = type;
    }

//    public List<Portfolio> getPortfolios() {
//        return portfolios;
//    }
//
//    public void setPortfolios(List<Portfolio> portfolios) {
//        this.portfolios = portfolios;
//    }


//    @Override
//    public String toString() {
//        return "AssetController{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", isin=" + isin +
//                ", ticker=" + ticker +
//                '}';
//    }
}
