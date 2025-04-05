package tn.esprit.cours.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.cours.model.Cours;
import tn.esprit.cours.service.CoursService;
import tn.esprit.cours.service.PdfGenerationService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cours")
public class CoursController {
    @Autowired
    private CoursService coursService;
    private PdfGenerationService pdfGenerationService;

    @GetMapping("/getall")
    public List<Cours> getAll() { return coursService.getAll(); }

    @GetMapping("/getbyId/{id}")
    public Optional<Cours> getById(@PathVariable Integer id) { return coursService.getById(id); }

    @PostMapping("/add")
    public Cours save(@RequestPart("cours") Cours cours,
                      @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        return coursService.save(cours, file);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer id) throws IOException {
        Map<String, Object> downloadData = coursService.downloadFile(id);
        Resource resource = (Resource) downloadData.get("resource");
        String filename = (String) downloadData.get("filename");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

    @DeleteMapping("/deleteById/{id}")
    public void delete(@PathVariable Integer id) { coursService.delete(id); }
    @GetMapping("/download-pdf/{id}")
    public ResponseEntity<byte[]> downloadAsPdf(@PathVariable Integer id) throws IOException {
        Cours cours = coursService.getById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        ByteArrayOutputStream pdfStream = pdfGenerationService.generatePdf(cours);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + cours.getNomCours() + ".pdf\"")
                .body(pdfStream.toByteArray());
    }
}

