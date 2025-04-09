package com.example.servicehamroun.entity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "departement")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_departement;

    private String nom_departement;

    @OneToOne
    @JoinColumn(name = "chef_departement_id")
    private Enseignant chef_departement;

    // Constructeurs
    public Departement() {
    }

    public Departement(String nom_departement, Enseignant chef_departement) {
        this.nom_departement = nom_departement;
        this.chef_departement = chef_departement;
    }

    // Getters et Setters
    public Long getId_departement() {
        return id_departement;
    }

    public void setId_departement(Long id_departement) {
        this.id_departement = id_departement;
    }

    public String getNom_departement() {
        return nom_departement;
    }

    public void setNom_departement(String nom_departement) {
        this.nom_departement = nom_departement;
    }

    public Enseignant getChef_departement() {
        return chef_departement;
    }

    public void setChef_departement(Enseignant chef_departement) {
        this.chef_departement = chef_departement;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id_departement=" + id_departement +
                ", nom_departement='" + nom_departement + '\'' +
                ", chef_departement=" + chef_departement +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departement that = (Departement) o;
        return Objects.equals(id_departement, that.id_departement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_departement);
    }


}
