package tn.esprit.cours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.cours.model.Cours;

public interface CoursRepository extends JpaRepository<Cours, Integer> {}
