package com.investscreener.investscreener.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column( name = "username", unique = true, length = 100)
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany( mappedBy = "users", // name of the field that owns the relationship
                                    // this generates the FK in Portfolio class
            cascade =  CascadeType.REMOVE, orphanRemoval = true )
    List<Portfolio> portfolios;

    @OneToOne(mappedBy = "users" , cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Profile profile;

}

// CascadeType.PERSIST : Persist related entities -> related entities are saved
// when new user is created . Not using it in this case so not to be able to create
// the user first and then add the portfolios
// CascadeType.REMOVE Delete portfolio FK in Portfolio if user is deleted
// orphanRemoval removes the related entity itself from db

// Uses InheritanceType.JOINED to store shared fields in 'plant'
// and unique fields in subclass tables

// , nullable = false only when you
// let hibernate generate the db tables at runtime
// Here we are using flyway
