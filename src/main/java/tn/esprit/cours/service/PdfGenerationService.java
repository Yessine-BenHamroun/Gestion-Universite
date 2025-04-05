package tn.esprit.cours.service;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import tn.esprit.cours.model.Cours;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfGenerationService {
    public ByteArrayOutputStream generatePdf(Cours cours) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Course Details");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Name: " + cours.getNomCours());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Description: " + cours.getDescription());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Credits: " + cours.getCredits());
            contentStream.endText();
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.save(out);
        document.close();

        return out;
    }
}