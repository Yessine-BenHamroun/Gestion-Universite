package com.example.servicehamroun.service;

import com.example.servicehamroun.entity.Departement;
import com.example.servicehamroun.entity.Enseignant;
import com.example.servicehamroun.repository.DepartementRepo;
import com.example.servicehamroun.repository.EnseignantRepo;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartementServiceImpl implements IDepartementService {

    private final DepartementRepo departementRepository;
    private final EnseignantRepo enseignantRepository;

    public DepartementServiceImpl(DepartementRepo departementRepository, EnseignantRepo enseignantRepository) {
        this.departementRepository = departementRepository;
        this.enseignantRepository = enseignantRepository;
    }

    @Override
    public Departement ajouterDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public void deleteDepartement(Long id) {
        departementRepository.deleteById(id);
    }

    @Override
    public Departement updateDepartement(Long id, Departement updatedDepartement) {
        Departement existingDepartement = departementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Département avec ID " + id + " non trouvé"));

        existingDepartement.setNom_departement(updatedDepartement.getNom_departement());
        existingDepartement.setChef_departement(updatedDepartement.getChef_departement());

        return departementRepository.save(existingDepartement);
    }

    @Override
    public Departement getDepartementById(Long id) {
        return departementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Département avec ID " + id + " non trouvé"));
    }

    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement assignerChefDepartement(Long departementId, Enseignant enseignant) {
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new EntityNotFoundException("Département avec ID " + departementId + " non trouvé"));

        if (departement.getChef_departement() != null) {
            throw new IllegalStateException("Ce département a déjà un chef assigné");
        }

        departement.setChef_departement(enseignant);
        enseignant.setEstChefDepartement(true);

        return departementRepository.save(departement);
    }

    @Override
    public Departement retirerChefDepartement(Long departementId) {
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new EntityNotFoundException("Département avec ID " + departementId + " non trouvé"));

        if (departement.getChef_departement() == null) {
            throw new IllegalStateException("Ce département n'a pas de chef assigné");
        }

        Enseignant ancienChef = departement.getChef_departement();
        ancienChef.setEstChefDepartement(false);
        departement.setChef_departement(null);

        return departementRepository.save(departement);
    }

    @Override
    public Departement affecterEnseignantADepartement(Long departementId, Long enseignantId) {
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new EntityNotFoundException("Département avec ID " + departementId + " non trouvé"));

        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new EntityNotFoundException("Enseignant avec ID " + enseignantId + " non trouvé"));

        enseignant.setDepartementDirige(departement);
        enseignantRepository.save(enseignant);

        return departement;
    }

    @Override
    public List<Enseignant> getEnseignantsByDepartement(Long departementId) {
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new EntityNotFoundException("Département avec ID " + departementId + " non trouvé"));

        // Implement the logic directly in the service instead of calling departement.getEnseignants()
        List<Enseignant> enseignants = new ArrayList<>();
        for (Enseignant enseignant : enseignantRepository.findAll()) {
            if (enseignant.getDepartementDirige() != null &&
                    enseignant.getDepartementDirige().getId_departement().equals(departementId)) {
                enseignants.add(enseignant);
            }
        }
        return enseignants;
    }

    @Override
    public Departement desaffecterEnseignantDeDepartement(Long departementId, Long enseignantId) {
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new EntityNotFoundException("Département avec ID " + departementId + " non trouvé"));

        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new EntityNotFoundException("Enseignant avec ID " + enseignantId + " non trouvé"));

        if (enseignant.getDepartementDirige() != null &&
                enseignant.getDepartementDirige().getId_departement().equals(departementId)) {
            enseignant.setDepartementDirige(null);
            enseignantRepository.save(enseignant);
        }

        return departement;
    }
}