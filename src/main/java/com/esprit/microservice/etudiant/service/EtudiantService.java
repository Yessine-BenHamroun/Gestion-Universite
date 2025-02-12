package com.esprit.microservice.etudiant.service;

import com.esprit.microservice.etudiant.models.Etudiant;
import com.esprit.microservice.etudiant.repository.EtudiantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService implements Iservice {
    @Autowired
    private EtudiantRepo etudiantRepo;
    @Override
    public Etudiant addEtudiant(Etudiant etudiant) {
        return  etudiantRepo.save(etudiant);
    }

    @Override
    public Etudiant getEtudiantById(int id) {
        return etudiantRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteEtudiant(int id) {
        if (etudiantRepo.existsById(id)) {
            etudiantRepo.deleteById(id);
        }
    }

    @Override
    public Etudiant updateEtudiant(Etudiant etudiant) {
        if (etudiantRepo.existsById(etudiant.getId_etudiant())) {
            return etudiantRepo.save(etudiant);
        }
        return null;
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepo.findAll();
    }
}