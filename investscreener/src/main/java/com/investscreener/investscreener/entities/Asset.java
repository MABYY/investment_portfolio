package com.investscreener.investscreener.entities;
import com.fasterxml.jackson.annotation.JsonView;
import com.investscreener.investscreener.controller.PublicView.PublicView;
import com.investscreener.investscreener.entities.enums.AssetType;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany(mappedBy = "assets") // name of the field that owns teh relationship
    private Set<Portfolio> portfolios = new HashSet<>();

}
