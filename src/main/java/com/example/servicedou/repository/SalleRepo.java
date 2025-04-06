 package com.example.servicedou.repository;



        import com.example.servicedou.entity.Salle;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;
        import java.util.Date;
        import java.util.List;

        import org.springframework.data.repository.query.Param;
@Repository
public interface SalleRepo extends JpaRepository<Salle, Long> {
    Salle findByNomSalle(String nomSalle);
    @Query("SELECT s FROM Salle s WHERE s.capacite >= :nbEtudiants " +
            "AND NOT EXISTS (SELECT e FROM Examen e WHERE e.salle.id = s.id " +
            "AND ABS(DATEDIFF(e.dateExamen, :date)) <= 1) " +
            "ORDER BY s.capacite ASC")
    List<Salle> findSallesDisponiblesParCapacite(
            @Param("date") Date date,
            @Param("nbEtudiants") int nbEtudiants
    );

}