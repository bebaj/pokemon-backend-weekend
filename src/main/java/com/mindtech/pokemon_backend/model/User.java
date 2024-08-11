package com.mindtech.pokemon_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="users")
public class User{
    @Id
    @Column
    private int id;
    @Column
    private String username;
    @Column
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pokemon> pokemons;


    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
        pokemon.setUser(this);
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}