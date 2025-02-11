package com.example.enseignant.services;

import com.example.enseignant.models.Departement;
import com.example.enseignant.models.Enseignant;
import com.example.enseignant.repositories.DepartementRepo;
import com.example.enseignant.repositories.EnseignantRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class DepartementService implements IDepartementService{
    EnseignantRepo enseignantRepo;
    DepartementRepo departementRepo;
    @Override
    public Departement addDepartement(Departement departement) {
        return departementRepo.save(departement);
    }
    @Override
    public Departement updateDepartement(Departement departement) {
        return departementRepo.save(departement);
    }
    @Override
    public void deleteDepartement(int id) {
        departementRepo.deleteById((long) id);
    }
    @Override
    public Departement getDepartementById(int id) {
        return departementRepo.findById((long) id).orElse(null);
    }
    @Override
    public Departement getDepartementByNom(String nom) {
        return departementRepo.findByNom(nom);
    }
    @Override
    public Departement getDepartementByEnseignant(int idEnseignant) {

        return departementRepo.findByEnseignant(String.valueOf(enseignantRepo.findById((long) idEnseignant).orElse(null)));
    }
    @Override
    public Departement affecterChefDepartement(int idDepartement, int idEnseignant) {

        return departementRepo.save(Objects.requireNonNull(departementRepo.findById((long) idDepartement).orElse(null)));
    }
}
