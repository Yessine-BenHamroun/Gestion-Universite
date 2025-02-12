package com.esprit.microservice.etudiant.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Etudiant implements Serializable {
    private static final long serialVersionUID =6 ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_etudiant;
    private String nom;
    private String prenom;
    private String email;
    private Date date_naissance;
    private String adresse;
    private String telephone;
    private String niveau;
    private Integer id_departement;

    public Etudiant(Integer id_etudiant, String nom, String prenom, String email, Date date_naissance, String adresse, String telephone, String niveau, Integer id_departement) {
        this.id_etudiant = id_etudiant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.niveau = niveau;
        this.id_departement = id_departement;
    }

    public Etudiant(Integer id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public Etudiant() {

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getNiveau() {
        return niveau;
    }

    public Integer getId_departement() {
        return id_departement;
    }

    public Integer getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(Integer id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setId_departement(Integer id_departement) {
        this.id_departement = id_departement;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id_etudiant=" + id_etudiant +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", date_naissance=" + date_naissance +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", niveau='" + niveau + '\'' +
                ", id_departement=" + id_departement +
                '}';
    }
}
