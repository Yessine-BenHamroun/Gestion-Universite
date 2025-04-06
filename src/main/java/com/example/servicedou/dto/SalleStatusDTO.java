package com.example.servicedou.dto;

import com.example.servicedou.entity.TypeSalle;
import java.util.List;

public class SalleStatusDTO {
    private Long id;
    private String nomSalle;
    private int capacite;
    private TypeSalle typeSalle;
    private List<ExamenDTO> examenList;

    public SalleStatusDTO(Long id, String nomSalle, int capacite, TypeSalle typeSalle, List<ExamenDTO> examenList) {
        this.id = id;
        this.nomSalle = nomSalle;
        this.capacite = capacite;
        this.typeSalle = typeSalle;
        this.examenList = examenList;
    }

    // Getters
    public Long getId() { return id; }
    public String getNomSalle() { return nomSalle; }
    public int getCapacite() { return capacite; }
    public TypeSalle getTypeSalle() { return typeSalle; }
    public List<ExamenDTO> getExamenList() { return examenList; }
}