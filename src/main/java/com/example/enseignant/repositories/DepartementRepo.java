package com.example.enseignant.repositories;

import com.example.enseignant.models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepo extends JpaRepository<Departement,Long> {
    Departement findByNom(String nom);
    Departement findByEnseignant(String enseignant);

}
