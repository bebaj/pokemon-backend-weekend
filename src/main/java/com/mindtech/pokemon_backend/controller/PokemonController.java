package com.mindtech.pokemon_backend.controller;

import com.mindtech.pokemon_backend.model.Pokemon;
import com.mindtech.pokemon_backend.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }

    @GetMapping("/{id}")
    public Pokemon getPokemon(@PathVariable("id") int id) {
        return pokemonService.getPokemonById(id);
    }

    @PostMapping
    public Pokemon savePokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.saveOrUpdate(pokemon);
    }

    @DeleteMapping("/{id}")
    public void deletePokemon(@PathVariable("id") int id) {
        pokemonService.delete(id);
    }
}
