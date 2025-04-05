package tn.esprit.cours.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCours;
    private String nomCours;
    private String description;
    private Integer credits;
    private Integer idEnseignant;
    private String filePath; // Add this field to store file location

    public Cours(String nomCours, String description, Integer credits, Integer idEnseignant, Integer idDepartement, Examen examen) {
        this.nomCours = nomCours;
        this.description = description;
        this.credits = credits;
        this.idEnseignant = idEnseignant;
        this.idDepartement = idDepartement;
        this.examen = examen;
    }

    private Integer idDepartement;

    @OneToOne(mappedBy = "cours", cascade = CascadeType.ALL)
    private Examen examen;

    public Integer getIdCours() {
        return idCours;
    }

    public void setIdCours(Integer idCours) {
        this.idCours = idCours;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Integer idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public Integer getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(Integer idDepartement) {
        this.idDepartement = idDepartement;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
}
