package tn.esprit.cours.service;

import org.springframework.core.io.Resource;  // Change from jakarta.annotation.Resource
import org.springframework.core.io.FileSystemResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.cours.model.Cours;
import tn.esprit.cours.repository.CoursRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CoursService {
    @Autowired
    private CoursRepository coursRepository;
    @Value("${upload.dir}") // Configure this in application.properties
    private String uploadDir;

    public List<Cours> getAll() {
        return coursRepository.findAll();
    }

    public Optional<Cours> getById(Integer id) {
        return coursRepository.findById(id);
    }

    public Cours save(Cours cours) {
        return coursRepository.save(cours);
    }

    public void delete(Integer id) {
        coursRepository.deleteById(id);
    }
    public Cours save(Cours cours, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            // Create upload directory if it doesn't exist
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            // Save file
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + File.separator + fileName;
            file.transferTo(new File(filePath));

            cours.setFilePath(filePath);
        }
        return coursRepository.save(cours);
    }

    public Map<String, Object> downloadFile(Integer id) throws FileNotFoundException {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (cours.getFilePath() == null) {
            throw new FileNotFoundException("No file attached to this course");
        }

        File file = new File(cours.getFilePath());
        Resource resource = new FileSystemResource(file);

        if (!resource.exists()) {
            throw new FileNotFoundException("File not found");
        }

        // Get the filename from the file path
        String filename = file.getName();

        // Return both the resource and filename
        return Map.of(
                "resource", resource,
                "filename", filename
        );
    }
}
