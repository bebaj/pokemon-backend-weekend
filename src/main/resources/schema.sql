-- Drop existing tables if they exist
DROP TABLE IF EXISTS user_pokemons;
DROP TABLE IF EXISTS pokemons;
DROP TABLE IF EXISTS users;

-- Create users table
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL
);

-- Create pokemons table
CREATE TABLE pokemons (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          weight DECIMAL(10, 2) NOT NULL,
                          height DECIMAL(10, 2) NOT NULL,
                          abilities TEXT -- Using TEXT to store abilities as a comma-separated string
);

-- Create user_pokemons table to manage the many-to-many relationship
CREATE TABLE user_pokemons (
                               user_id INT NOT NULL,
                               pokemon_id INT NOT NULL,
                               PRIMARY KEY (user_id, pokemon_id),
                               FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                               FOREIGN KEY (pokemon_id) REFERENCES pokemons(id) ON DELETE CASCADE
);
