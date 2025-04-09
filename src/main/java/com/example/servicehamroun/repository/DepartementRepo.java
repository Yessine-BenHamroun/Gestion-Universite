package com.example.servicehamroun.repository;

import com.example.servicehamroun.entity.Departement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepo extends JpaRepository<Departement, Long> {
    // Méthodes personnalisées si nécessaire
    List<Departement> findAll();
}

