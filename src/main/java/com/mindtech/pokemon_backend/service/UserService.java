package com.mindtech.pokemon_backend.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.mindtech.pokemon_backend.model.Pokemon;
import com.mindtech.pokemon_backend.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mindtech.pokemon_backend.model.User;
import com.mindtech.pokemon_backend.repository.UserRepository;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Pokemon> getUserPokemons(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPokemons();
    }
    public void catchPokemon(Integer userId, String pokemonName) {
        // Check if the Pokémon already exists in the user's collection
        Optional<Pokemon> existingPokemon = pokemonRepository.findById(userId);
        if (existingPokemon.isPresent()) {
            throw new RuntimeException("You have already caught this Pokémon");
        }

        // Fetch Pokémon details from PokeAPI
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonName.toLowerCase();ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Pokemon not found");
        }

        // Parse the response to get necessary details
        JSONObject pokemonData = new JSONObject(response.getBody());
        String name = pokemonData.getString("name");
        String spriteUrl = pokemonData.getJSONObject("sprites").getString("front_default");

        // Create and save the Pokémon
        Pokemon pokemon = new Pokemon();
        pokemon.setName(name);
        pokemon.setSpriteUrl(spriteUrl);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        pokemon.setUser(user);
        user.addPokemon(pokemon);


        pokemonRepository.save(pokemon);
        userRepository.save(user);
    }

    public void releasePokemon(Integer userId, Integer pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(Math.toIntExact(pokemonId)).orElseThrow(() -> new RuntimeException("Pokemon not found"));
        if (!Objects.equals(pokemon.getUser().getId(), userId)) {
            throw new RuntimeException("This pokemon does not belong to the user");
        }
        pokemonRepository.delete(pokemon);
    }
}
