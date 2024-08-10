package com.mindtech.pokemon_backend.service;

import com.mindtech.pokemon_backend.model.Pokemon;
import com.mindtech.pokemon_backend.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    public Pokemon getPokemonById(int id) {
        return pokemonRepository.findById(id).orElse(null);
    }

    public Pokemon saveOrUpdate(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public void delete(int id) {
        pokemonRepository.deleteById(id);
    }
}
