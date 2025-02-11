package com.example.enseignant.services;

import com.example.enseignant.models.Enseignant;

public interface IEnseignantService {
    Enseignant addEnseignant(Enseignant enseignant);
    Enseignant updateEnseignant(Enseignant enseignant);
    void deleteEnseignant(int id);
    Enseignant getAllEnseignants();
    Enseignant getEnseignantById(int id);
    Enseignant getEnseignantByEmail(String email);
    Enseignant getEnseignantByNomAndPrenom(String nom, String prenom);
    Enseignant getEnseignantByTelephone(String telephone);
    Enseignant getEnseignantByDepartement(int idDepartement);
    Enseignant affecterEnseignantADepartement(int idEnseignant, int idDepartement);
}
