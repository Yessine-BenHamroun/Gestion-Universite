package com.example.enseignant.repositories;

import com.example.enseignant.models.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepo extends JpaRepository<Enseignant,Long> {
    Enseignant findByNomAndPrenom(String nom, String prenom);

    Enseignant findByEmail(String email);

    Enseignant findByTelephone(String telephone);

    Enseignant findByDepartement(String s);
}
