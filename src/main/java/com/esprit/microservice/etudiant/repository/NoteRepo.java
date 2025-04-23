package com.esprit.microservice.etudiant.repository;

import com.esprit.microservice.etudiant.models.Etudiant;
import com.esprit.microservice.etudiant.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note, Integer> {
    List<Note> findByEtudiant(Etudiant etudiant);

}
