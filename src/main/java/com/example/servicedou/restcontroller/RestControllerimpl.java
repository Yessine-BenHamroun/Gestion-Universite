package   com.example.servicedou.restcontroller;
import java.util.HashMap;
import java.util.Map;
import com.example.servicedou.entity.Examen;
import com.example.servicedou.entity.Salle;
import com.example.servicedou.entity.TypeSalle;
import com.example.servicedou.service.IService;
import com.example.servicedou.service.HolidayService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import java.util.stream.Collectors;
import com.example.servicedou.dto.ExamenDTO;
import com.example.servicedou.dto.SalleStatusDTO;
import com.example.servicedou.repository.SalleRepo;
import java.util.List;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.example.servicedou.external.HolidayApiResponse;

@RestController
@RequestMapping("/salles")

public class RestControllerimpl {
@Autowired
    private  IService Iservice;
    @Autowired
    private SalleRepo salleRepository;
    @Autowired
    private HolidayService holidayService;

    @GetMapping("/salles/status")
    public List<SalleStatusDTO> getSallesStatus() {
        return salleRepository.findAll().stream()
                .map(salle -> new SalleStatusDTO(
                        salle.getId(),
                        salle.getNomSalle(),
                        salle.getCapacite(),
                        salle.getTypeSalle(), // Ajout du type de salle
                        salle.getExamenList().stream()
                                .map(examen -> new ExamenDTO(
                                        examen.getId(),
                                        examen.getDateExamen(),
                                        examen.getTypeExamen(),
                                        examen.getCoefficient(), // Ajout du coefficient
                                        examen.getNbEtudiants()  // Ajout du nombre d'étudiants
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
    @GetMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode() throws WriterException, IOException {
        String apiUrl = "http://localhost:8082/salles/salles/status"; // URL de votre endpoint
        int width = 300;
        int height = 300;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(apiUrl, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(pngData);
    }
    @PostMapping("/ajouter")
    public Salle ajouterSalle(@RequestBody Salle salle) {
        // Vérification des champs obligatoires
        if (salle.getNomSalle() == null || salle.getNomSalle().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la salle ne peut pas être vide.");
        }

        if (salle.getCapacite() <= 0) {
            throw new IllegalArgumentException("La capacité de la salle doit être un nombre positif.");
        }

        if (salle.getTypeSalle() == null) {
            throw new IllegalArgumentException("Le type de la salle est obligatoire.");
        }

        System.out.println("Salle reçue : " + salle);
        return Iservice.ajouterSalle(salle);
    }
    @PutMapping("/examens/{examenId}/salle/{salleId}")
    public ResponseEntity<Map<String, Object>> affecterExamenASalle(
            @PathVariable Long examenId,
            @PathVariable Long salleId) {

        try {
            // 1. Perform the assignment
            Examen examen = Iservice.affecterExamenASalle(examenId, salleId);

            // 2. Create the response
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "✅ Affectation réussie : Examen #" + examenId
                    + " → Salle '" + examen.getSalle().getNomSalle() + "'");
            response.put("examen", examen);

            return ResponseEntity.ok(response);

        } catch (EntityNotFoundException e) {
            // 3. Handle errors
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "❌ Échec : " + e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }





    @GetMapping("/salles")
    public List<Salle> getAllSalles() {
        return Iservice.getAllSalles();
    }

    @GetMapping("salle/{id}")
    public Salle getSalleById(@PathVariable Long id) {
        return Iservice.getSalleById(id); // Gère l'exception si la salle n'est pas trouvée
    }
    @PutMapping("/updates/{id}")
    public Salle updateSalle(@PathVariable Long id, @RequestBody Salle updatedSalle) {
        return Iservice.updateSalle(id, updatedSalle);
    }




    @DeleteMapping("deletes/{id}")
    public void deleteSalle(@PathVariable Long id) {
        Iservice.deleteSalle(id); // Lève une exception si la salle n'existe pas
    }
    @PostMapping("/exam")
    public ResponseEntity<?> ajouterExamen(@RequestBody Examen examen) {
        try {
            Examen nouvelExamen = Iservice.ajouterExamen(examen);
            return ResponseEntity.ok(nouvelExamen);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur lors de la création de l'examen");
        }
    }

    @GetMapping("/exams")
    public List<Examen> getAllExamens() {
        return Iservice.getAllExamens();
    }

    @GetMapping("exaMm/{id}")
    public Examen getExamenById(@PathVariable Long id) {
        return Iservice.getExamenById(id);
    }

    @GetMapping("/examm/{salleId}")
    public List<Examen> getExamensBySalle(@PathVariable Long salleId) {
        return Iservice.getExamensBySalle(salleId);
    }

    @PutMapping("/examen/{id}")
    public ResponseEntity<?> updateExamen(@PathVariable Long id, @RequestBody Examen updatedExamen) {
        try {
            Examen examen = Iservice.updateExamen(id, updatedExamen);
            return ResponseEntity.ok(examen);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la mise à jour");
        }
    }

    @DeleteMapping("delete/{id}")
    public void deleteExamen(@PathVariable Long id) {
        Iservice.deleteExamen(id);
    }
    @PostMapping("/examens/auto")
    public ResponseEntity<?> planifierExamenAuto(@RequestBody Examen examen) {
        try {
            Examen examenPlanifie = Iservice.planifierExamenAvecSalleAuto(examen);
            return ResponseEntity.ok(examenPlanifie);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur interne est survenue");
        }
    }
    @PutMapping("/examens/{examenId}/desaffecter")
    public ResponseEntity<?> desaffecterExamenDeSalle(@PathVariable Long examenId) {
        try {
            Examen examenDesaffecte = Iservice.desaffecterExamenDeSalle(examenId);
            return ResponseEntity.ok(examenDesaffecte);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Examen non trouvé avec l'ID: " + examenId);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
    // Dans RestControllerimpl.java
    @GetMapping("/jours-feries")
    public List<HolidayApiResponse.Holiday> getHolidays(
            @RequestParam int year,
            @RequestParam(required = false, defaultValue = "MA") String country) {
        return holidayService.getHolidaysForYear(country, year);
    }

}
