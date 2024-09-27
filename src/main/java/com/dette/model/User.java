package com.dette.model;

import java.io.Serializable;
import java.util.Collection;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "user") 
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;

    @Column(name = "username", nullable = false, length = 20)
    private String username;
    
    @Column(name = "email", nullable = false, length = 60)
    private String email;
    
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "role", nullable = false, length = 20) // Ajoutez cette ligne pour le rôle
    private String role; // Champ pour stocker le rôle de l'utilisateur

    @OneToMany(mappedBy = "user", fetch = jakarta.persistence.FetchType.EAGER) 
    private Collection<Client> clients;

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getRole() { // Méthode pour obtenir le rôle
        return role;
    }

    public void setRole(String role) { // Méthode pour définir le rôle
        this.role = role;
    }

    public Collection<Client> getClients() {
        return clients;
    }

    public void setClients(Collection<Client> clients) {
        this.clients = clients;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
