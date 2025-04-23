package com.esprit.microservice.etudiant.service;

import com.esprit.microservice.etudiant.models.Etudiant;
import com.esprit.microservice.etudiant.models.Inscription;
import com.esprit.microservice.etudiant.repository.EtudiantRepo;
import com.esprit.microservice.etudiant.repository.InscriptionRepo;
import com.esprit.microservice.etudiant.repository.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService implements Iservice {
    @Autowired
    private EtudiantRepo etudiantRepo;
    @Autowired
    private InscriptionRepo inscriptionRepo;
    @Autowired
    private NoteRepo noteRepo;

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
        List<Etudiant> etudiants = etudiantRepo.findAll();
        System.out.println("Fetched " + etudiants.size() + " students from DB");
        return etudiants;
    }
    @Override
    public Inscription assignInscriptionToEtudiant(Integer inscriptionId, Integer etudiantId) {
        // 1. Find both entities
        Inscription inscription = inscriptionRepo.findById(inscriptionId)
                .orElseThrow(() -> new RuntimeException("Inscription not found with id: " + inscriptionId));

        Etudiant etudiant = etudiantRepo.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id: " + etudiantId));

        // 2. Update relationship
        inscription.setEtudiant(etudiant);

        // 3. Save changes
        return inscriptionRepo.save(inscription);
    }
    @Override
    public Inscription desaffecterInscription(Integer etudiantId, Integer inscriptionId) {

        Inscription inscription = inscriptionRepo.findById(inscriptionId)
                .orElseThrow(() -> new RuntimeException("Inscription not found"));

        System.out.println("Inscription ID: " + inscription.getId_inscription());
        System.out.println("Linked Etudiant ID: " + (inscription.getEtudiant() != null ? inscription.getEtudiant().getId_etudiant() : "null"));

        if (inscription.getEtudiant() != null &&
                inscription.getEtudiant().getId_etudiant().equals(etudiantId)) {

            inscription.setEtudiant(null);
            return inscriptionRepo.save(inscription);
        } else {
            return null;
        }
    }




}