package com.esprit.microservice.etudiant.service;

import com.esprit.microservice.etudiant.models.Etudiant;
import com.esprit.microservice.etudiant.models.Inscription;

import java.util.List;

public interface Iservice {
    Etudiant addEtudiant(Etudiant etudiant);
    Etudiant getEtudiantById(int id);
    void deleteEtudiant(int id);
    Etudiant updateEtudiant(Etudiant etudiant);
    List<Etudiant> getAllEtudiants();


}
