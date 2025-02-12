package com.esprit.microservice.etudiant.controller;

import com.esprit.microservice.etudiant.models.Inscription;
import com.esprit.microservice.etudiant.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/inscriptions")
public class InscriptionController {


 @Autowired
    private InscriptionService inscriptionService;

    @GetMapping("/getAll")
    public List<Inscription> getAllInscriptions() {
        return inscriptionService.getAllInscriptions();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Inscription> getInscriptionById(@PathVariable int id) {

        Inscription inscription = inscriptionService.getInscriptionById(id);
        return (inscription != null) ? ResponseEntity.ok(inscription) : ResponseEntity.notFound().build();

    }

    @PostMapping("/addInscription")
    public Inscription addInscription(@RequestBody Inscription inscription) {
        return inscriptionService.addInscription(inscription);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Inscription> updateInscription(@PathVariable Integer id, @RequestBody Inscription updatedInscription) {
        Inscription inscription = inscriptionService.updateInscription(id, updatedInscription);
        return inscription != null ? ResponseEntity.ok(inscription) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInscription(@PathVariable Integer id) {
        inscriptionService.deleteInscription(id);
        return ResponseEntity.noContent().build();
    }
}


