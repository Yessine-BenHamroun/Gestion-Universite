package com.example.servicedou.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomSalle;
    private int capacite;

    @Enumerated(EnumType.STRING)
    private TypeSalle typeSalle;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Examen> examenList = new ArrayList<>();

    // Constructeurs
    public Salle() {
    }

    public Salle(String nomSalle, int capacite, TypeSalle typeSalle) {
        this.nomSalle = nomSalle;
        this.capacite = capacite;
        this.typeSalle = typeSalle;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public TypeSalle getTypeSalle() {
        return typeSalle;
    }

    public void setTypeSalle(TypeSalle typeSalle) {
        this.typeSalle = typeSalle;
    }

    public List<Examen> getExamenList() {
        return examenList;
    }

    public void setExamenList(List<Examen> examenList) {
        this.examenList = examenList;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "id=" + id +
                ", nomSalle='" + nomSalle + '\'' +
                ", capacite=" + capacite +
                ", typeSalle=" + typeSalle +
                '}';
    }
}