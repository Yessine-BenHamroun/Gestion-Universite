package com.example.enseignant.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Departement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_departement;
    private String nom_departement;
    @OneToOne(cascade = CascadeType.ALL)
    private Enseignant chef_departement;

    public Departement() {
    }

    public Departement(int id_departement, String nom_departement, Enseignant chef_departement) {
        this.id_departement = id_departement;
        this.nom_departement = nom_departement;
        this.chef_departement = chef_departement;
    }

    public int getId_departement() {
        return id_departement;
    }

    public void setId_departement(int id_departement) {
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
        return id_departement == that.id_departement && Objects.equals(nom_departement, that.nom_departement) && Objects.equals(chef_departement, that.chef_departement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_departement, nom_departement, chef_departement);
    }
}
