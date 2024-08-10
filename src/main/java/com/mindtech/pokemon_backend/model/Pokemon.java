package com.mindtech.pokemon_backend.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int weight;
    private int height;

    @ElementCollection
    private List<String> abilities;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public Pokemon(){

    }
    public Pokemon(Long id, String name, int weight, int height, List<String> abilities, User user) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.abilities = abilities;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
