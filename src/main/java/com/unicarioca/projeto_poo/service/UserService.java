package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.user.User;
import com.unicarioca.projeto_poo.domain.user.UserCategory;
import com.unicarioca.projeto_poo.domain.user.UserRequestDTO;
import com.unicarioca.projeto_poo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(UUID id){
        return userRepository.findById(id).get();
    }

    public User createUser(UserRequestDTO userDTO){
        User user = new User();

        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setRole(UserCategory.valueOf(userDTO.role().toUpperCase()));

        userRepository.save(user);

        return user;
    }

    public User updateUser(UUID idUser, UserRequestDTO userDTO){
        User user = userRepository.findById(idUser).get();

        if(user != null){
            user.setName(userDTO.name());
            user.setEmail(userDTO.email());
            user.setPassword(userDTO.password());
            user.setRole(UserCategory.valueOf(userDTO.role().toUpperCase()));

            userRepository.saveAndFlush(user);
        }

        return user;
    }

    public void deleteUser(UUID id){
        userRepository.deleteById(id);
    }




}
