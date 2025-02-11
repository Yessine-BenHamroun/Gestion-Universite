package com.example.enseignant.controllers;
import com.example.enseignant.models.Enseignant;
import com.example.enseignant.services.IDepartementService;
import com.example.enseignant.models.Departement;
import com.example.enseignant.services.IEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enseignant")
public class EnseignantREST {

        IEnseignantService enseignantService;

        @PostMapping("/add-enseignant")
        public Enseignant addEnseignant(@RequestBody Enseignant enseignant) {
            return enseignantService.addEnseignant(enseignant);
        }
        @PutMapping("/update-enseignant")
        public Enseignant updateEnseignant(@RequestBody Enseignant enseignant) {
            return enseignantService.updateEnseignant(enseignant);
        }
        @DeleteMapping("/delete-enseignant/{id}")
        public void deleteEnseignant(@PathVariable("id") int id) {
            enseignantService.deleteEnseignant(id);
        }
        @GetMapping("/get-all-enseignants")
        public Enseignant getAllEnseignants() {
            return enseignantService.getAllEnseignants();
        }
        @GetMapping("/get-enseignant/{id}")
        public Enseignant getEnseignantById(@PathVariable("id") int id) {
            return enseignantService.getEnseignantById(id);
        }
        @GetMapping("/get-by-email/{email}")
        public Enseignant getEnseignantByEmail(@PathVariable("email") String email) {
            return enseignantService.getEnseignantByEmail(email);
        }
        @GetMapping("/get-by-nom-prenom/{nom}/{prenom}")
        public Enseignant getEnseignantByNomAndPrenom(@PathVariable("nom") String nom, @PathVariable("prenom") String prenom) {
            return enseignantService.getEnseignantByNomAndPrenom(nom, prenom);
        }
        @GetMapping("/get-by-telephone/{telephone}")
        public Enseignant getEnseignantByTelephone(@PathVariable("telephone") String telephone) {
            return enseignantService.getEnseignantByTelephone(telephone);
        }
        @GetMapping("/get-by-departement/{idDepartement}")
        public Enseignant getEnseignantByDepartement(@PathVariable("idDepartement") int idDepartement) {
            return enseignantService.getEnseignantByDepartement(idDepartement);
        }
        @PutMapping("/affecter-enseignant-departement/{idEnseignant}/{idDepartement}")
        public Enseignant affecterEnseignantADepartement(@PathVariable("idEnseignant") int idEnseignant, @PathVariable("idDepartement") int idDepartement) {
            return enseignantService.affecterEnseignantADepartement(idEnseignant, idDepartement);
        }
}
