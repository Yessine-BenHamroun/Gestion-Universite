package com.esprit.microservice.etudiant.controller;

import com.esprit.microservice.etudiant.models.Etudiant;
import com.esprit.microservice.etudiant.service.Iservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        return ResponseEntity.ok(etudiants);
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
}