package com.unicarioca.projeto_poo.controller;

import com.unicarioca.projeto_poo.domain.user.User;
import com.unicarioca.projeto_poo.domain.user.UserRequestDTO;
import com.unicarioca.projeto_poo.repository.UserRepository;
import com.unicarioca.projeto_poo.service.UserService;
import org.hibernate.annotations.PartitionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findById/{idUser}")
    public User getUserById(@PathVariable UUID idUser){
        return userService.getUserById(idUser);
    }

    @PostMapping("/")
    public User createUser(@RequestBody UserRequestDTO userDTO){
        return userService.createUser(userDTO);
    }

    @PutMapping("/{idUser}")
    public User updateUser(@PathVariable UUID idUser, @RequestBody UserRequestDTO userDTO){
        return userService.updateUser(idUser, userDTO);
    }

    @DeleteMapping("/{idUser}")
    public void deleteUser(@PathVariable UUID idUser){
        userService.deleteUser(idUser);
    }
}
