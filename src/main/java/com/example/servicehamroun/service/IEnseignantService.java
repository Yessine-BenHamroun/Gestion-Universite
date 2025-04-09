package com.example.servicehamroun.service;
import java.util.List;
import com.example.servicehamroun.entity.Enseignant;

public interface IEnseignantService {
    Enseignant ajouterEnseignant(Enseignant enseignant);
    void deleteEnseignant(Long id);
    Enseignant updateEnseignant(Long id, Enseignant updatedEnseignant); 
    Enseignant getEnseignantById(Long id);
    List<Enseignant> getAllEnseignants();
    Enseignant affecterEnseignantADepartement(Long enseignantId, Long departementId);
    Enseignant definirChefDepartement(Long enseignantId, Long departementId);
    Enseignant getChefDepartement(Long departementId);
    boolean isChefDepartement(Long enseignantId, Long departementId);
    Enseignant retirerChefDepartement(Long departementId);
}

