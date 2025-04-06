package com.example.servicedou.repository;

        import com.example.servicedou.entity.Examen;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import com.example.servicedou.entity.Salle;
        import java.util.Date;
        import java.util.List;

@Repository
public interface ExamenRepo extends JpaRepository<Examen, Long> {
    List<Examen> findBySalleId(Long salleId); // Récupérer les examens d'une salle

}

