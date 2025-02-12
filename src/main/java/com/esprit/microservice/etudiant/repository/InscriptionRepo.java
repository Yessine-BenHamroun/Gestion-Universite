package com.esprit.microservice.etudiant.repository;

import com.esprit.microservice.etudiant.models.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepo extends JpaRepository<Inscription, Integer> {
}
