package tn.esprit.cours.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.cours.model.Salle;
import tn.esprit.cours.service.SalleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salle")
public class SalleController {
    @Autowired
    private SalleService salleService;

    @GetMapping("/getall")
    public List<Salle> getAll() { return salleService.getAll(); }

    @GetMapping("/get/{id}")
    public Optional<Salle> getById(@PathVariable Integer id) { return salleService.getById(id); }

    @PostMapping("/add")
    public Salle save(@RequestBody Salle salle) { return salleService.save(salle); }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) { salleService.delete(id); }
}
