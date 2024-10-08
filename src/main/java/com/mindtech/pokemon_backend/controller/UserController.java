package com.mindtech.pokemon_backend.controller;

import java.util.List;

import com.mindtech.pokemon_backend.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mindtech.pokemon_backend.model.User;
import com.mindtech.pokemon_backend.service.UserService;
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/user")
    private List<User> getAllUser()
    {
        return userService.getAllUser();
    }
    @GetMapping("/user/{id}")
    private User getUser(@PathVariable("id") int id)
    {
        return userService.getUserById(id);
    }
    @DeleteMapping("/user/{id}")
    private void deleteUser(@PathVariable("id") int id)
    {
        userService.delete(id);
    }
    @PostMapping("/user")
    private int saveUser(@RequestBody User user)
    {
        userService.saveOrUpdate(user);
        return user.getId();
    }
    @GetMapping("/{userId}/pokemons")
    public List<Pokemon> getUserPokemons(@PathVariable("userId") int userId) {
        return userService.getUserPokemons(userId);
    }

    @PostMapping("/{userId}/pokemons")
    public Pokemon addPokemonToUser(@PathVariable("userId") int userId, @RequestBody Pokemon pokemon) {
        return userService.addPokemonToUser(userId, pokemon);
    }

    @DeleteMapping("/pokemons/{pokemonId}")
    public void removePokemonFromUser(@PathVariable("pokemonId") int pokemonId) {
        userService.removePokemonFromUser(pokemonId);
    }

}
