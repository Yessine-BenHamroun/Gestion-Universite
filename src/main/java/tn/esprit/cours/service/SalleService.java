package tn.esprit.cours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.cours.model.Salle;
import tn.esprit.cours.repository.SalleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SalleService {
    @Autowired
    private SalleRepository salleRepository;

    public List<Salle> getAll() { return salleRepository.findAll(); }
    public Optional<Salle> getById(Integer id) { return salleRepository.findById(id); }
    public Salle save(Salle salle) { return salleRepository.save(salle); }
    public void delete(Integer id) { salleRepository.deleteById(id); }
}
