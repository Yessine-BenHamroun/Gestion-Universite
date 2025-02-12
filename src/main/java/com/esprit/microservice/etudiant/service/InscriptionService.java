package com.esprit.microservice.etudiant.service;

import com.esprit.microservice.etudiant.models.Inscription;
import com.esprit.microservice.etudiant.repository.InscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService implements IinService {
    @Autowired
    private InscriptionRepo inscriptionRepo;

    @Override
    public Inscription addInscription(Inscription inscription) {
        return inscriptionRepo.save(inscription);
    }

    @Override
    public Optional<Inscription> getInscriptionById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void deleteInscription(Integer id) {

    }

    @Override
    public Inscription updateInscription(Integer id, Inscription updatedInscription) {
        return null;
    }


    public Inscription getInscriptionById(int id) {
        return inscriptionRepo.findById(id).orElse(null);
    }


    public void deleteInscription(int id) {
        if (inscriptionRepo.existsById(id)) {
            inscriptionRepo.deleteById(id);
        }
    }


    public Inscription updateInscription(Inscription inscription) {
        if (inscriptionRepo.existsById(inscription.getId_inscription())) {
            return inscriptionRepo.save(inscription);
        }
        return null;
    }

    @Override
    public List<Inscription> getAllInscriptions() {
        return inscriptionRepo.findAll();
    }
}