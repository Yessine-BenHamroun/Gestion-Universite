package com.example.enseignant.services;

import com.example.enseignant.models.Enseignant;
import com.example.enseignant.repositories.DepartementRepo;
import com.example.enseignant.repositories.EnseignantRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnseginantService implements IEnseignantService{
    EnseignantRepo enseignantRepo;
    DepartementRepo departementRepo;
    @Override
    public Enseignant addEnseignant(Enseignant enseignant) {
        return enseignantRepo.save(enseignant);
    }
    @Override
    public Enseignant updateEnseignant(Enseignant enseignant) {
        return enseignantRepo.save(enseignant);
    }
    @Override
    public void deleteEnseignant(int id) {
        enseignantRepo.deleteById((long) id);
    }
    @Override
    public Enseignant getAllEnseignants() {
        return (Enseignant) enseignantRepo.findAll();
    }

    @Override
    public Enseignant getEnseignantById(int id) {

        return enseignantRepo.findById((long) id).orElse(null);
    }

    @Override
    public Enseignant getEnseignantByEmail(String email) {
        return enseignantRepo.findByEmail(email);
    }
    @Override
    public Enseignant getEnseignantByNomAndPrenom(String nom, String prenom) {
        return enseignantRepo.findByNomAndPrenom(nom, prenom);
    }
    @Override
    public Enseignant getEnseignantByTelephone(String telephone) {
        return enseignantRepo.findByTelephone(telephone);
    }
    @Override
    public Enseignant getEnseignantByDepartement(int idDepartement) {

        return enseignantRepo.findByDepartement(String.valueOf(departementRepo.findById((long) idDepartement).orElse(null)));
    }
    @Override
    public Enseignant affecterEnseignantADepartement(int idEnseignant, int idDepartement) {

            Enseignant enseignant = enseignantRepo.findById((long) idEnseignant).orElse(null);
            enseignant.setDepartement(departementRepo.findById((long) idDepartement).orElse(null));
            return enseignantRepo.save(enseignant);
    }
}
