package com.example.servicehamroun.restcontroller;

import com.example.servicehamroun.entity.Departement;
import com.example.servicehamroun.entity.Enseignant;
import com.example.servicehamroun.service.IDepartementService;
import com.example.servicehamroun.service.IEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EnsDepRestController {

    @Autowired
    private IEnseignantService enseignantService;

    @Autowired
    private IDepartementService departementService;

    // Enseignant Endpoints
    @PostMapping("/enseignants/create")
    public ResponseEntity<Enseignant> createEnseignant(@RequestBody Enseignant enseignant) {
        return new ResponseEntity<>(enseignantService.ajouterEnseignant(enseignant), HttpStatus.CREATED);
    }

    @DeleteMapping("/enseignants/delete/{id}")
    public ResponseEntity<Void> deleteEnseignant(@PathVariable Long id) {
        enseignantService.deleteEnseignant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/enseignants/update/{id}")
    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignant) {
        return new ResponseEntity<>(enseignantService.updateEnseignant(id, enseignant), HttpStatus.OK);
    }

    @GetMapping("/enseignants/{id}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable Long id) {
        return new ResponseEntity<>(enseignantService.getEnseignantById(id), HttpStatus.OK);
    }

    @GetMapping("/enseignants/all")
    public ResponseEntity<List<Enseignant>> getAllEnseignants() {
        return new ResponseEntity<>(enseignantService.getAllEnseignants(), HttpStatus.OK);
    }

    // Departement Endpoints
    @PostMapping("/departements/create")
    public ResponseEntity<Departement> createDepartement(@RequestBody Departement departement) {
        return new ResponseEntity<>(departementService.ajouterDepartement(departement), HttpStatus.CREATED);
    }

    @DeleteMapping("/departements/delete/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Long id) {
        departementService.deleteDepartement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/departements/update/{id}")
    public ResponseEntity<Departement> updateDepartement(@PathVariable Long id, @RequestBody Departement departement) {
        return new ResponseEntity<>(departementService.updateDepartement(id, departement), HttpStatus.OK);
    }

    @GetMapping("/departements/{id}")
    public ResponseEntity<Departement> getDepartementById(@PathVariable Long id) {
        return new ResponseEntity<>(departementService.getDepartementById(id), HttpStatus.OK);
    }

    @GetMapping("/departements/all")
    public ResponseEntity<List<Departement>> getAllDepartements() {
        return new ResponseEntity<>(departementService.getAllDepartements(), HttpStatus.OK);
    }

    // Relationship Management Endpoints
    @PostMapping("/departements/{departementId}/assign/enseignants/{enseignantId}")
    public ResponseEntity<Departement> assignEnseignantToDepartement(
            @PathVariable Long departementId,
            @PathVariable Long enseignantId) {
        return new ResponseEntity<>(
                departementService.affecterEnseignantADepartement(departementId, enseignantId),
                HttpStatus.OK
        );
    }

    @GetMapping("/departements/{id}/enseignants/list")
    public ResponseEntity<List<Enseignant>> getEnseignantsByDepartementId(@PathVariable Long id) {
        return new ResponseEntity<>(departementService.getEnseignantsByDepartement(id), HttpStatus.OK);
    }

    @DeleteMapping("/departements/{departementId}/remove/enseignants/{enseignantId}")
    public ResponseEntity<Departement> removeEnseignantFromDepartement(
            @PathVariable Long departementId,
            @PathVariable Long enseignantId) {
        return new ResponseEntity<>(
                departementService.desaffecterEnseignantDeDepartement(departementId, enseignantId),
                HttpStatus.OK
        );
    }

    @PostMapping("/departements/{departementId}/assign/chef")
    public ResponseEntity<Departement> assignChefDepartement(
            @PathVariable Long departementId,
            @RequestBody Enseignant enseignant) {
        return new ResponseEntity<>(
                departementService.assignerChefDepartement(departementId, enseignant),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/departements/{departementId}/remove/chef")
    public ResponseEntity<Departement> removeChefDepartement(@PathVariable Long departementId) {
        return new ResponseEntity<>(
                departementService.retirerChefDepartement(departementId),
                HttpStatus.OK
        );
    }
}