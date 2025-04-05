package tn.esprit.cours.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExamen;
    private String dateExamen;
    private String typeExamen;
    private Float coefficient;

    public Examen(String dateExamen, String typeExamen, Float coefficient, Cours cours, Salle salle) {
        this.dateExamen = dateExamen;
        this.typeExamen = typeExamen;
        this.coefficient = coefficient;
        this.cours = cours;
        this.salle = salle;
    }

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public String getDateExamen() {
        return dateExamen;
    }

    public void setDateExamen(String dateExamen) {
        this.dateExamen = dateExamen;
    }

    public String getTypeExamen() {
        return typeExamen;
    }

    public void setTypeExamen(String typeExamen) {
        this.typeExamen = typeExamen;
    }

    public Float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Float coefficient) {
        this.coefficient = coefficient;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    @OneToOne
    @JoinColumn(name = "id_cours")
    private Cours cours;

    @OneToOne
    @JoinColumn(name = "id_salle")
    private Salle salle;
}

