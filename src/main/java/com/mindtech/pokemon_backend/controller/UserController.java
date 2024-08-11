package com.mindtech.pokemon_backend.controller;

import com.mindtech.pokemon_backend.dto.RegisterRequest;
import com.mindtech.pokemon_backend.model.Pokemon;
import com.mindtech.pokemon_backend.model.User;
import com.mindtech.pokemon_backend.service.UserService;
import com.mindtech.pokemon_backend.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;



    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(user);  // Return the registered user data
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails);

            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);  // Return the JWT token in the response body

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(null);  // Return 401 status for invalid credentials
        }
    }


    @GetMapping("/{userId}/pokemons")
    public List<Pokemon> getUserPokemons(@PathVariable Integer userId) {
        return userService.getUserPokemons(userId);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);  // Returns the list of users in the response body
    }

    @DeleteMapping("/{userId}/pokemons/{pokemonId}")
    public void releasePokemon(@PathVariable Integer userId, @PathVariable Integer pokemonId) {
        userService.releasePokemon(userId, pokemonId);
    }
}
