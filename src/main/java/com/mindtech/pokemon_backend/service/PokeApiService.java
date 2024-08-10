package com.mindtech.pokemon_backend.service;

import com.mindtech.pokemon_backend.model.Pokemon;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokeApiService {

    private final String apiUrl = "https://pokeapi.co/api/v2/pokemon/";

    public Pokemon fetchPokemon(String name) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl + name, Pokemon.class);
    }
}
