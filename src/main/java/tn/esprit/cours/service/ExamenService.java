package tn.esprit.cours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.cours.model.Examen;
import tn.esprit.cours.repository.ExamenRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {
    @Autowired
    private ExamenRepository examenRepository;

    public List<Examen> getAll() { return examenRepository.findAll(); }
    public Optional<Examen> getById(Integer id) { return examenRepository.findById(id); }
    public Examen save(Examen examen) { return examenRepository.save(examen); }
    public void delete(Integer id) { examenRepository.deleteById(id); }
}
