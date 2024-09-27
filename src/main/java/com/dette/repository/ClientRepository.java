package com.dette.repository;

import com.dette.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Vous pouvez ajouter des méthodes de requêtes spécifiques ici
}
