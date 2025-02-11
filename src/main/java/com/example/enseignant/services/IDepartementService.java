package com.example.enseignant.services;

import com.example.enseignant.models.Departement;

public interface IDepartementService {
    Departement addDepartement(Departement departement);
    Departement updateDepartement(Departement departement);
    void deleteDepartement(int id);
    Departement getDepartementById(int id);
    Departement getDepartementByNom(String nom);
    Departement getDepartementByEnseignant(int idEnseignant);
    Departement affecterChefDepartement(int idDepartement, int idEnseignant);
}
