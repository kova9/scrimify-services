package com.scrimify.services.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Game {
    @Id
    @Column(length = 36)
    private String gameId;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String developerName;

}
