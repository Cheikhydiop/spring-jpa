package com.dette.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dette.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // Méthodes personnalisées peuvent être ajoutées ici si nécessaire
}
