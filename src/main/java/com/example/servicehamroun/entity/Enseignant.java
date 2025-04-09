package com.example.servicehamroun.entity;
import jakarta.persistence.*;


@Entity
@Table(name = "enseignant")
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private boolean estChefDepartement;

    @OneToOne(mappedBy = "chef_departement")
    private Departement departementDirige;
        
        // Constructeurs
        public Enseignant() {
        }
    
        public Enseignant(String nom, String prenom, String email, String telephone) {
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.telephone = telephone;
            this.estChefDepartement = false;
        }
    
        // Getters et Setters
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getNom() {
            return nom;
        }
    
        public void setNom(String nom) {
            this.nom = nom;
        }
    
        public String getPrenom() {
            return prenom;
        }
    
        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }
    
        public String getEmail() {
            return email;
        }
    
        public void setEmail(String email) {
            this.email = email;
        }
    
        public String getTelephone() {
            return telephone;
        }
    
        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
    
        public Departement getDepartementDirige() {
            return departementDirige;
        }
    
        public void setDepartementDirige(Departement departementDirige) {
            this.departementDirige = departementDirige;
        }
    
        public boolean isEstChefDepartement() {
            return estChefDepartement;
        }
    
        public void setEstChefDepartement(boolean estChefDepartement) {
            this.estChefDepartement = estChefDepartement;
        }
    
}
