package com.esprit.microservice.etudiant.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Note implements Serializable {
    private static final long serialVersionUID =6 ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_note;
    private Float valeur;
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiant etudiant;
    private Integer id_examen;

    public Note() {
    }

    public Integer getId_note() {
        return id_note;
    }

    public void setId_note(Integer id_note) {
        this.id_note = id_note;
    }

    public Float getValeur() {
        return valeur;
    }

    public void setValeur(Float valeur) {
        this.valeur = valeur;
    }


    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Integer getId_examen() {
        return id_examen;
    }

    public void setId_examen(Integer id_examen) {
        this.id_examen = id_examen;
    }
    @Override
    public String toString() {
        return "Note{" +
                "id_note=" + id_note +
                ", valeur=" + valeur +
                ", etudiant=" + etudiant +
                ", id_examen=" + id_examen +
                '}';
    }
}
