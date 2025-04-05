package tn.esprit.cours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.cours.model.Salle;

public interface SalleRepository extends JpaRepository<Salle, Integer> {}