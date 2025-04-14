package com.investscreener.investscreener.entities;

import jakarta.persistence.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "bio")
    private String bio;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private Users users;
}
