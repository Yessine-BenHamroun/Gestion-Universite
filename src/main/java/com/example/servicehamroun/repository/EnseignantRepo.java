package com.example.servicehamroun.repository;

import com.example.servicehamroun.entity.Enseignant;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepo extends JpaRepository<Enseignant, Long> {
    // Méthodes personnalisées si nécessaire
    List<Enseignant> findAll();
}

