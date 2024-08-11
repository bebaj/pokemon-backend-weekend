package com.mindtech.pokemon_backend.controller;
import com.mindtech.pokemon_backend.dto.RegisterRequest;
import com.mindtech.pokemon_backend.model.Pokemon;
import com.mindtech.pokemon_backend.model.User;
import com.mindtech.pokemon_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        // Spring Security handles login, this method can be customized if needed.
        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/{userId}/pokemons")
    public List<Pokemon> getUserPokemons(@PathVariable Integer userId) {
        return userService.getUserPokemons(userId);
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{userId}/pokemons/{pokemonId}")
    public void releasePokemon(@PathVariable Integer userId, @PathVariable Integer pokemonId) {
        userService.releasePokemon(userId, pokemonId);
    }
}
