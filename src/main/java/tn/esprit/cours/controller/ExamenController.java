package tn.esprit.cours.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.cours.model.Examen;
import tn.esprit.cours.service.ExamenService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/examen")
public class ExamenController {
    @Autowired
    private ExamenService examenService;

    @GetMapping("/getall")
    public List<Examen> getAll() { return examenService.getAll(); }

    @GetMapping("/get/{id}")
    public Optional<Examen> getById(@PathVariable Integer id) { return examenService.getById(id); }

    @PostMapping("/add")
    public Examen save(@RequestBody Examen examen) { return examenService.save(examen); }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) { examenService.delete(id); }
}
