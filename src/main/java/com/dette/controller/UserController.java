package com.dette.controller;

import com.dette.model.User;
import com.dette.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    
    

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        try {
            List<User> users = userRepository.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword())); // Chiffre le mot de passe
//        User savedUser = userRepository.save(user); // Enregistre l'utilisateur
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser); // Retourne l'utilisateur créé
//    }
//
//    
//    
//    
//    
//    
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Chiffre le mot de passe
        User savedUser = userRepository.save(user); // Enregistre l'utilisateur
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser); // Retourne l'utilisateur créé
    }

    // D'autres méthodes pour mettre à jour, supprimer, etc. peuvent être ajoutées ici
}
