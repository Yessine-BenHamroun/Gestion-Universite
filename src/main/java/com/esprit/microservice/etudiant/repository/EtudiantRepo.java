package com.esprit.microservice.etudiant.repository;

import com.esprit.microservice.etudiant.models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepo extends JpaRepository<Etudiant , Integer> {
}
