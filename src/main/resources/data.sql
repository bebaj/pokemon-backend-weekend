INSERT INTO users (username, password)
VALUES
    ('ash_ketchum', 'pikachu123'),
    ('asd', 'asd'),
    ('misty_waterflower', 'starmie456'),
    ('brock_pewter', 'onix789');
INSERT INTO pokemons (name, weight, height, abilities)
VALUES
    ('Pikachu', 6.0, 0.4, 'Static, Lightning Rod'),
    ('Bulbasaur', 6.9, 0.7, 'Overgrow, Chlorophyll'),
    ('Charmander', 8.5, 0.6, 'Blaze, Solar Power'),
    ('Squirtle', 9.0, 0.5, 'Torrent, Rain Dish');

INSERT INTO user_pokemons (user_id, pokemon_id)
VALUES
    (1, 1), -- Ash has Pikachu
    (1, 2), -- Ash also has Bulbasaur
    (2, 4), -- Misty has Squirtle
    (3, 3); -- Brock has Charmander