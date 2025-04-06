 package com.example.servicedou.service;


import com.example.servicedou.entity.Examen;
import com.example.servicedou.entity.Salle;


import java.util.List;

public interface IService {
    public Salle ajouterSalle(Salle salle);
    public void deleteSalle(Long id);
    public Salle updateSalle(Long id, Salle updatedSalle);
    public Salle getSalleById(Long id);
    public List<Salle> getAllSalles();
    Examen ajouterExamen(Examen examen);
    Examen getExamenById(Long id);
    List<Examen> getAllExamens();
    List<Examen> getExamensBySalle(Long salleId);
    Examen updateExamen(Long id, Examen examen);
    void deleteExamen(Long id);
    Examen affecterExamenASalle(Long examenId, Long salleId);
    Examen planifierExamenAvecSalleAuto(Examen examen);
    Examen desaffecterExamenDeSalle(Long examenId);
}
