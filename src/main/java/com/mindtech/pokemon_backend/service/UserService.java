package com.mindtech.pokemon_backend.service;

import java.util.ArrayList;
import java.util.List;

import com.mindtech.pokemon_backend.model.Pokemon;
import com.mindtech.pokemon_backend.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindtech.pokemon_backend.model.User;
import com.mindtech.pokemon_backend.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PokemonRepository pokemonRepository;

    public List<User> getAllUser()
    {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }
    public User getUserById(int id)
    {
        return userRepository.findById(id).orElse(null);
    }
    public void saveOrUpdate(User user)
    {
        userRepository.save(user);
    }
    public void delete(int id)
    {
        userRepository.deleteById(id);
    }

    public List<Pokemon> getUserPokemons(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getPokemons();
        }
        return null;
    }

    public Pokemon addPokemonToUser(int userId, Pokemon pokemon) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return pokemonRepository.save(pokemon);
        }
        return null;
    }

    public void removePokemonFromUser(int pokemonId) {
        pokemonRepository.deleteById(pokemonId);
    }

}