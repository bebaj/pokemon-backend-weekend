package com.mindtech.pokemon_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int weight;
    private int height;
    private String spriteUrl;
    private String type;


    @ElementCollection
    private List<String> abilities;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public Pokemon(){

    }

    public Pokemon(Long id, String name, int weight, int height, String spriteUrl, String type, List<String> abilities,
                   User user) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.spriteUrl = spriteUrl;
        this.type = type;
        this.abilities = abilities;
        this.user = user;
    }
}
