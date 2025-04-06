package com.example.servicedou.dto;

import com.example.servicedou.entity.TypeExamen;
import java.util.Date;

public class ExamenDTO {
    private Long id;
    private Date dateExamen;
    private TypeExamen typeExamen;
    private float coefficient;
    private int nbEtudiants;

    public ExamenDTO(Long id, Date dateExamen, TypeExamen typeExamen, float coefficient, int nbEtudiants) {
        this.id = id;
        this.dateExamen = dateExamen;
        this.typeExamen = typeExamen;
        this.coefficient = coefficient;
        this.nbEtudiants = nbEtudiants;
    }

    // Getters
    public Long getId() { return id; }
    public Date getDateExamen() { return dateExamen; }
    public TypeExamen getTypeExamen() { return typeExamen; }
    public float getCoefficient() { return coefficient; }
    public int getNbEtudiants() { return nbEtudiants; }
}