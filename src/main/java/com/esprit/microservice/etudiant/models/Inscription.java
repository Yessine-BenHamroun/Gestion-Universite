package com.esprit.microservice.etudiant.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Inscription implements Serializable {
    private static final long serialVersionUID =6 ;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_inscription;
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiant etudiant;
    private Integer id_cours;
    private String annee_academique;

    public Inscription() {
    }

    public Inscription(Integer id_inscription, Etudiant etudiant, Integer id_cours, String annee_academique) {
        this.id_inscription = id_inscription;
        this.etudiant = etudiant;
        this.id_cours = id_cours;
        this.annee_academique = annee_academique;
    }

    public Integer getId_inscription() {
        return id_inscription;
    }

    public void setId_inscription(Integer id_inscription) {
        this.id_inscription = id_inscription;
    }





    public Integer getId_cours() {
        return id_cours;
    }

    public void setId_cours(Integer id_cours) {
        this.id_cours = id_cours;
    }

    public String getAnnee_academique() {
        return annee_academique;
    }

    public void setAnnee_academique(String annee_academique) {
        this.annee_academique = annee_academique;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;

    }
    @Override
    public String toString() {
        return "Inscription{" +
                "id_inscription=" + id_inscription +
                ", etudiant=" + etudiant +
                ", id_cours=" + id_cours +
                ", annee_academique='" + annee_academique + '\'' +
                '}';
    }
}
