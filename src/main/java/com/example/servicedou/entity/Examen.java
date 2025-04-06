package com.example.servicedou.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "examen")
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExamen;

    @Enumerated(EnumType.STRING)
    private TypeExamen typeExamen;

    private float coefficient;
    private int nbEtudiants;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name = "salle_id")
    private Salle salle;

    // Constructeurs
    public Examen() {
    }

    public Examen(Date dateExamen, TypeExamen typeExamen, float coefficient, int nbEtudiants) {
        this.dateExamen = dateExamen;
        this.typeExamen = typeExamen;
        this.coefficient = coefficient;
        this.nbEtudiants = nbEtudiants;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateExamen() {
        return dateExamen;
    }

    public void setDateExamen(Date dateExamen) {
        this.dateExamen = dateExamen;
    }

    public TypeExamen getTypeExamen() {
        return typeExamen;
    }

    public void setTypeExamen(TypeExamen typeExamen) {
        this.typeExamen = typeExamen;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public int getNbEtudiants() {
        return nbEtudiants;
    }

    public void setNbEtudiants(int nbEtudiants) {
        this.nbEtudiants = nbEtudiants;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    @Override
    public String toString() {
        return "Examen{" +
                "id=" + id +
                ", dateExamen=" + dateExamen +
                ", typeExamen=" + typeExamen +
                ", coefficient=" + coefficient +
                ", nbEtudiants=" + nbEtudiants +
                '}';
    }
}