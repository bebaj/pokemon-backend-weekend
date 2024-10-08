package com.mindtech.pokemon_backend.repository;

import com.mindtech.pokemon_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
}