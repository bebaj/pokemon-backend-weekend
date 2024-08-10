package com.mindtech.pokemon_backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.mindtech.pokemon_backend.model.Pokemon;
import com.mindtech.pokemon_backend.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mindtech.pokemon_backend.model.User;
import com.mindtech.pokemon_backend.repository.UserRepository;

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

    public void releasePokemon(Integer userId, Integer pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(Math.toIntExact(pokemonId)).orElseThrow(() -> new RuntimeException("Pokemon not found"));
        if (!Objects.equals(pokemon.getUser().getId(), userId)) {
            throw new RuntimeException("This pokemon does not belong to the user");
        }
        pokemonRepository.delete(pokemon);
    }
}
