package com.example.servicedou.service;
import java.text.SimpleDateFormat;
import com.example.servicedou.entity.Examen;
import com.example.servicedou.entity.Salle;
import com.example.servicedou.repository.ExamenRepo;
import com.example.servicedou.repository.SalleRepo;
import jakarta.persistence.EntityNotFoundException;
import java.util.Comparator;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.stream.Collectors;

import java.util.Date;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServiceImpl implements IService {
    private final HolidayService holidayService;
    private final SalleRepo salleRepository;
    private final ExamenRepo examenRepo;

    public ServiceImpl(SalleRepo salleRepository, ExamenRepo examenRepo,HolidayService holidayService) {
        this.holidayService = holidayService;
        this.salleRepository = salleRepository;
        this.examenRepo = examenRepo;
    }

   @Override

   public Salle ajouterSalle(Salle salle) {
       return salleRepository.save(salle);
   }


    public List<Salle> getAllSalles() {
        return salleRepository.findAll(); // Retourne toutes les salles
    }

    public Salle getSalleById(Long id) {
        // Si la salle n'est pas trouvée, on lève une exception
        return salleRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Salle avec ID " + id + " non trouvée"));
    }

    @Override
    public Salle updateSalle(Long id, Salle updatedSalle) {
        Salle existingSalle = salleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Salle avec ID " + id + " non trouvée"));

        // Mettre à jour uniquement les champs fournis
        if (updatedSalle.getNomSalle() != null) {
            existingSalle.setNomSalle(updatedSalle.getNomSalle());
        }
        if (updatedSalle.getCapacite() > 0) {
            existingSalle.setCapacite(updatedSalle.getCapacite());
        }
        if (updatedSalle.getTypeSalle() != null) {
            existingSalle.setTypeSalle(updatedSalle.getTypeSalle());
        }

        return salleRepository.save(existingSalle);
    }


    public void deleteSalle(Long id) {
        // Vérifie si la salle existe avant de la supprimer
        if (!salleRepository.existsById(id)) {
            throw new EntityNotFoundException("Salle avec ID " + id + " non trouvée");
        }
        salleRepository.deleteById(id); // Supprime la salle
    }
    @Override
    public Examen ajouterExamen(Examen examen) {
        if (examen.getDateExamen() == null) {
            throw new IllegalArgumentException("La date de l'examen est obligatoire");
        }

        try {
            if (holidayService.isHoliday(examen.getDateExamen(), "TN")) {
                String holidayName = holidayService.getHolidayName(examen.getDateExamen(), "TN");
                String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(examen.getDateExamen());
                throw new IllegalArgumentException("Création impossible le " + formattedDate +
                        " : " + (holidayName != null ? holidayName : "Jour férié en Tunisie"));
            }
        } catch (HolidayService.HolidayApiException e) {
            throw new IllegalArgumentException("Service des jours fériés indisponible");
        }

        if (examen.getDateExamen().before(new Date())) {
            throw new IllegalArgumentException("La date de l'examen ne peut pas être dans le passé");
        }

        examen.setSalle(null);
        return examenRepo.save(examen);
    }
    @Override
    public Examen getExamenById(Long id) {
        return examenRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Examen avec ID " + id + " non trouvé"));
    }

    @Override
    public List<Examen> getAllExamens() {
        return examenRepo.findAll();
    }

    @Override
    public List<Examen> getExamensBySalle(Long salleId) {
        return examenRepo.findBySalleId(salleId);
    }

    @Override
    public Examen updateExamen(Long id, Examen updatedExamen) {
        Examen existingExamen = examenRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Examen avec ID " + id + " non trouvé"));

        // Mettre à jour uniquement les champs fournis
        if (updatedExamen.getDateExamen() != null) {
            existingExamen.setDateExamen(updatedExamen.getDateExamen());
        }
        if (updatedExamen.getTypeExamen() != null) {
            existingExamen.setTypeExamen(updatedExamen.getTypeExamen());
        }
        if (updatedExamen.getCoefficient() > 0) {
            existingExamen.setCoefficient(updatedExamen.getCoefficient());
        }
        if (updatedExamen.getNbEtudiants() > 0) {
            existingExamen.setNbEtudiants(updatedExamen.getNbEtudiants());
        }
        if (updatedExamen.getSalle() != null) {
            existingExamen.setSalle(updatedExamen.getSalle());
        }

        return examenRepo.save(existingExamen);
    }
    @Override
    public void deleteExamen(Long id) {
        if (!examenRepo.existsById(id)) {
            throw new EntityNotFoundException("Examen avec ID " + id + " non trouvé");
        }
        examenRepo.deleteById(id);
    }
    @Override
    public Examen affecterExamenASalle(Long examenId, Long salleId) {
        // Vérifier si l'examen existe
        Examen examen = examenRepo.findById(examenId)
                .orElseThrow(() -> new EntityNotFoundException("Examen avec ID " + examenId + " non trouvé"));

        // Vérifier si la salle existe
        Salle salle = salleRepository.findById(salleId)
                .orElseThrow(() -> new EntityNotFoundException("Salle avec ID " + salleId + " non trouvée"));

        // Associer l'examen à la salle
        examen.setSalle(salle);

        // Enregistrer la mise à jour
        return examenRepo.save(examen);
    }

    // Dans ServiceImpl.java
    @Override
    public Examen planifierExamenAvecSalleAuto(Examen examen) {

        // Validation
        if (examen.getDateExamen() == null) {
            throw new IllegalArgumentException("La date de l'examen est requise");
        }
        if (examen.getNbEtudiants() <= 0) {
            throw new IllegalArgumentException("Le nombre d'étudiants doit être positif");
        }

        // 1. Find available rooms with sufficient capacity and no time conflict
        List<Salle> sallesDisponibles = salleRepository.findSallesDisponiblesParCapacite(
                examen.getDateExamen(),
                examen.getNbEtudiants()
        );

        // 2. Handle no available rooms
        if (sallesDisponibles.isEmpty()) {
            // Find all rooms with sufficient capacity (regardless of time)
            List<Salle> sallesCapaciteSuffisante = salleRepository.findAll().stream()
                    .filter(s -> s.getCapacite() >= examen.getNbEtudiants())
                    .toList();

            String message;
            if (sallesCapaciteSuffisante.isEmpty()) {
                message = String.format(
                        "Aucune salle n'a la capacité requise (%d places). Capacité maximale disponible: %d",
                        examen.getNbEtudiants(),
                        salleRepository.findAll().stream()
                                .mapToInt(Salle::getCapacite)
                                .max()
                                .orElse(0)
                );
            } else {
                message = String.format(
                        "Aucune salle disponible à la date %s. Salles avec capacité suffisante mais occupées: %s",
                        examen.getDateExamen(),
                        sallesCapaciteSuffisante.stream()
                                .map(s -> s.getNomSalle() + " (" + s.getCapacite() + " places)")
                                .collect(Collectors.joining(", "))
                );
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        // 3. Select the optimal room (smallest sufficient capacity)
        Salle salleOptimal = sallesDisponibles.stream()
                .min(Comparator.comparingInt(Salle::getCapacite))
                .orElseThrow();

        // 4. Assign the room and save
        examen.setSalle(salleOptimal);
        return examenRepo.save(examen);
    }@Override
    public Examen desaffecterExamenDeSalle(Long examenId) {
        // 1. Récupérer l'examen
        Examen examen = examenRepo.findById(examenId)
                .orElseThrow(() -> new EntityNotFoundException("Examen non trouvé avec l'ID: " + examenId));

        // 2. Vérifier si l'examen a déjà une salle affectée
        if (examen.getSalle() == null) {
            throw new IllegalStateException("Cet examen n'a pas de salle affectée");
        }

        // 3. Désaffecter la salle
        Salle ancienneSalle = examen.getSalle();
        examen.setSalle(null);

        // 4. Sauvegarder
        Examen examenModifie = examenRepo.save(examen);

        // 5. Optionnel: Retirer l'examen de la liste des examens de la salle
        if (ancienneSalle != null) {
            ancienneSalle.getExamenList().removeIf(e -> e.getId().equals(examenId));
            salleRepository.save(ancienneSalle);
        }

        return examenModifie;
    }}

