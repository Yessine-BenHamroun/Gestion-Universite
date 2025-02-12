package com.esprit.microservice.etudiant.service;

import com.esprit.microservice.etudiant.models.Inscription;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IinService {
    Inscription addInscription(Inscription inscription);
    Optional<Inscription> getInscriptionById(Integer id);
    void deleteInscription(Integer id);
    Inscription updateInscription(Integer id, Inscription updatedInscription);
    List<Inscription> getAllInscriptions();
}
