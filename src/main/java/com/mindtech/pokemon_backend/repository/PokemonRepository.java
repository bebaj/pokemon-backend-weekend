package com.mindtech.pokemon_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mindtech.pokemon_backend.model.Pokemon;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
}