package com.mindtech.pokemon_backend.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindtech.pokemon_backend.model.User;
import com.mindtech.pokemon_backend.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
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
}