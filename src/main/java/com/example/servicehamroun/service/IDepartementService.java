package com.example.servicehamroun.service;

import java.util.List;
import com.example.servicehamroun.entity.Departement;
import com.example.servicehamroun.entity.Enseignant;

public interface IDepartementService {
    Departement ajouterDepartement(Departement departement);
    void deleteDepartement(Long id);
    Departement updateDepartement(Long id, Departement updatedDepartement);
    Departement getDepartementById(Long id);
    List<Departement> getAllDepartements();

    // Remove this method that has no parameter
    // List<Enseignant> getEnseignants();

    Departement affecterEnseignantADepartement(Long departementId, Long enseignantId);
    List<Enseignant> getEnseignantsByDepartement(Long departementId);
    Departement desaffecterEnseignantDeDepartement(Long departementId, Long enseignantId);
    Departement assignerChefDepartement(Long departementId, Enseignant enseignant);
    Departement retirerChefDepartement(Long departementId);
}