package com.esprit.microservice.etudiant.repository;

import com.esprit.microservice.etudiant.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<Note, Integer> {
}
