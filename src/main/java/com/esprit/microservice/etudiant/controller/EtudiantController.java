package com.esprit.microservice.etudiant.controller;

import com.esprit.microservice.etudiant.models.Etudiant;
import com.esprit.microservice.etudiant.models.Inscription;
import com.esprit.microservice.etudiant.service.Iservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/etudiants")

public class EtudiantController {
    @Autowired
    private Iservice etudiantService;

    // Add a new student
    @PostMapping("/add")
    public ResponseEntity<Etudiant> addEtudiant(@RequestBody Etudiant etudiant) {
        Etudiant newEtudiant = etudiantService.addEtudiant(etudiant);
        return ResponseEntity.ok(newEtudiant);
    }

    // Get student by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable int id) {
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        return (etudiant != null) ? ResponseEntity.ok(etudiant) : ResponseEntity.notFound().build();
    }

    // Get all students
    @GetMapping("/getAll")
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    // Update a student
    @PutMapping("/update/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable int id, @RequestBody Etudiant etudiant) {
        etudiant.setId_etudiant(id);
        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);
        return (updatedEtudiant != null) ? ResponseEntity.ok(updatedEtudiant) : ResponseEntity.notFound().build();
    }

    // Delete a student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable int id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{etudiantId}/inscriptions/{inscriptionId}/assign")
    public ResponseEntity<Inscription> assignInscriptionToEtudiant(
            @PathVariable Integer etudiantId,
            @PathVariable Integer inscriptionId) {

        Inscription updatedInscription = etudiantService.assignInscriptionToEtudiant(etudiantId, inscriptionId);

        return ResponseEntity.ok(updatedInscription);
    }
    @PutMapping("/desaffect-inscription/{idInscription}/from-etudiant/{idEtudiant}")
    public ResponseEntity<Inscription> desaffecterInscriptionFromEtudiant(
            @PathVariable("idEtudiant") Integer idEtudiant,
            @PathVariable("idInscription") Integer idInscription) {

        System.out.println(">>> Desaffecting inscription " + idInscription + " from etudiant " + idEtudiant);

        Inscription updatedInscription = etudiantService.desaffecterInscription(idEtudiant, idInscription);

        if (updatedInscription != null) {
            return ResponseEntity.ok(updatedInscription);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}