package com.dette.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    private String secretKey; // Inject secret key from application properties

    @PostMapping
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        // Journalisez la demande entrante
        System.out.println("Tentative de connexion pour l'utilisateur : " + authRequest.getUsername());

        // Vérifiez si les champs sont vides
        if (authRequest.getUsername() == null || authRequest.getUsername().isEmpty() || 
            authRequest.getPassword() == null || authRequest.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Le nom d'utilisateur et le mot de passe ne doivent pas être vides");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            String token = generateToken(authentication);
            return ResponseEntity.ok("Utilisateur connecté avec succès : " + authRequest.getUsername() + ", Token : " + token);
        } catch (AuthenticationException e) {
            System.err.println("Échec de l'authentification : " + e.getMessage());
            return ResponseEntity.status(401).body("Nom d'utilisateur ou mot de passe invalide");
        } catch (Exception e) {
            // Capturez toute autre exception et journalisez-la
            System.err.println("Erreur lors de la connexion : " + e.getMessage());
            return ResponseEntity.status(500).body("Erreur interne du serveur");
        }
    }


    private String generateToken(Authentication authentication) {
        // Définir la date d'expiration du token
        long expirationTime = 1000 * 60 * 60; // 1 heure
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);

        // Créer le payload du token
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", authentication.getName());
        // Ajoutez d'autres informations si nécessaire

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey) // Utilisation de la clé secrète injectée
                .compact();
    }

    public static class AuthRequest {
        private String username;
        private String password;

        // Getters et Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
