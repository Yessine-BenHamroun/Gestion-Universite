package tn.esprit.cours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.cours.model.Examen;

public interface ExamenRepository extends JpaRepository<Examen, Integer> {}
