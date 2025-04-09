package com.example.servicehamroun.service;

import com.example.servicehamroun.entity.Departement;
import com.example.servicehamroun.entity.Enseignant;
import com.example.servicehamroun.repository.EnseignantRepo;
import com.example.servicehamroun.repository.DepartementRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnseignantServiceImpl implements IEnseignantService {

    private final EnseignantRepo enseignantRepository;
    private final DepartementRepo departementRepository;

    public EnseignantServiceImpl(EnseignantRepo enseignantRepository, DepartementRepo departementRepository) {
        this.enseignantRepository = enseignantRepository;
        this.departementRepository = departementRepository;
    }

    @Override
    public Enseignant ajouterEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    @Override
    public void deleteEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }

    @Override
    public Enseignant updateEnseignant(Long id, Enseignant updatedEnseignant) {
        Enseignant existingEnseignant = enseignantRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Enseignant avec ID " + id + " non trouvé"));
        
        existingEnseignant.setNom(updatedEnseignant.getNom());
        existingEnseignant.setPrenom(updatedEnseignant.getPrenom());
        existingEnseignant.setEmail(updatedEnseignant.getEmail());
        existingEnseignant.setTelephone(updatedEnseignant.getTelephone());
        
        return enseignantRepository.save(existingEnseignant);
    }

    @Override
    public Enseignant getEnseignantById(Long id) {
        return enseignantRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Enseignant avec ID " + id + " non trouvé"));
    }

    @Override
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    @Override
    public Enseignant affecterEnseignantADepartement(Long enseignantId, Long departementId) {
        Enseignant enseignant = enseignantRepository.findById(enseignantId)
            .orElseThrow(() -> new EntityNotFoundException("Enseignant avec ID " + enseignantId + " non trouvé"));
            
        Departement departement = departementRepository.findById(departementId)
            .orElseThrow(() -> new EntityNotFoundException("Département avec ID " + departementId + " non trouvé"));

        enseignant.setDepartementDirige(departement);
        return enseignantRepository.save(enseignant);
    }

    @Override
    public Enseignant definirChefDepartement(Long enseignantId, Long departementId) {
        Enseignant enseignant = enseignantRepository.findById(enseignantId)
            .orElseThrow(() -> new EntityNotFoundException("Enseignant avec ID " + enseignantId + " non trouvé"));
            
        Departement departement = departementRepository.findById(departementId)
            .orElseThrow(() -> new EntityNotFoundException("Département avec ID " + departementId + " non trouvé"));

        // Vérifier si l'enseignant appartient au département
        if (enseignant.getDepartementDirige() == null || !enseignant.getDepartementDirige().getId_departement().equals(departementId)) {
            throw new IllegalStateException("L'enseignant doit appartenir au département pour en devenir le chef");
        }

        // Retirer l'ancien chef si existe
        if (departement.getChef_departement() != null) {
            departement.getChef_departement().setEstChefDepartement(false);
        }

        enseignant.setEstChefDepartement(true);
        departement.setChef_departement(enseignant);
        
        departementRepository.save(departement);
        return enseignantRepository.save(enseignant);
    }

    @Override
    public Enseignant getChefDepartement(Long departementId) {
        Departement departement = departementRepository.findById(departementId)
            .orElseThrow(() -> new EntityNotFoundException("Département avec ID " + departementId + " non trouvé"));
            
        if (departement.getChef_departement() == null) {
            throw new EntityNotFoundException("Aucun chef n'est assigné à ce département");
        }
        
        return departement.getChef_departement();
    }

    @Override
    public boolean isChefDepartement(Long enseignantId, Long departementId) {
        Enseignant enseignant = enseignantRepository.findById(enseignantId)
            .orElseThrow(() -> new EntityNotFoundException("Enseignant avec ID " + enseignantId + " non trouvé"));
            
        Departement departement = departementRepository.findById(departementId)
            .orElseThrow(() -> new EntityNotFoundException("Département avec ID " + departementId + " non trouvé"));

        return departement.getChef_departement() != null && 
               departement.getChef_departement().getId().equals(enseignantId) &&
               enseignant.isEstChefDepartement();
    }

    @Override
    public Enseignant retirerChefDepartement(Long departementId) {
        Departement departement = departementRepository.findById(departementId)
            .orElseThrow(() -> new EntityNotFoundException("Département avec ID " + departementId + " non trouvé"));
            
        if (departement.getChef_departement() == null) {
            throw new EntityNotFoundException("Aucun chef n'est assigné à ce département");
        }

        Enseignant ancienChef = departement.getChef_departement();
        ancienChef.setEstChefDepartement(false);
        departement.setChef_departement(null);
        
        departementRepository.save(departement);
        return enseignantRepository.save(ancienChef);
    }
}
