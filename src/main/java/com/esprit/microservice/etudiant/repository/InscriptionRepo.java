package com.esprit.microservice.etudiant.repository;

import com.esprit.microservice.etudiant.models.Etudiant;
import com.esprit.microservice.etudiant.models.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscriptionRepo extends JpaRepository<Inscription, Integer> {
    List<Inscription> findByEtudiant(Etudiant etudiant);

}
