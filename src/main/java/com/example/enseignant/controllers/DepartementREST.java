package com.example.enseignant.controllers;

import com.example.enseignant.services.IDepartementService;
import com.example.enseignant.models.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departement")
public class DepartementREST {

    IDepartementService departementService;

    @PostMapping("/add")
    public Departement addDepartement(@RequestBody Departement departement) {
        return departementService.addDepartement(departement);
    }
    @PutMapping("/update-departement")
    public Departement updateDepartement(@RequestBody Departement departement) {
        return departementService.updateDepartement(departement);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteDepartement(@PathVariable("id") int id) {
        departementService.deleteDepartement(id);
    }
    @GetMapping("/get/{id}")
    public Departement getDepartementById(@PathVariable("id") int id) {
        return departementService.getDepartementById(id);
    }
    @GetMapping("/get-by-nom/{nom}")
    public Departement getDepartementByNom(@PathVariable("nom") String nom) {
        return departementService.getDepartementByNom(nom);
    }
    @GetMapping("/get-by-enseignant/{idEnseignant}")
    public Departement getDepartementByEnseignant(@PathVariable("idEnseignant") int idEnseignant) {
        return departementService.getDepartementByEnseignant(idEnseignant);
    }
    @PutMapping("/affecter-chef-departement/{idDepartement}/{idEnseignant}")
    public Departement affecterChefDepartement(@PathVariable("idDepartement") int idDepartement, @PathVariable("idEnseignant") int idEnseignant) {
        return departementService.affecterChefDepartement(idDepartement, idEnseignant);
    }
}