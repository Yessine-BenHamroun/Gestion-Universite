package com.esprit.microservice.etudiant.service;

import com.esprit.microservice.etudiant.models.Etudiant;
import com.esprit.microservice.etudiant.models.Mail;
import com.esprit.microservice.etudiant.models.Note;
import com.esprit.microservice.etudiant.repository.EtudiantRepo;
import com.esprit.microservice.etudiant.repository.NoteRepo;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService implements INService{
    @Autowired
    private NoteRepo noteRepo;
    @Autowired
    private EtudiantRepo etudiantRepo;
    @Autowired
    private IEmailSenderService emailSenderServiceIMP;

    @Override
    public Note addNote(Note note) {
        return noteRepo.save(note);
    }

    @Override
    public Note getNoteById(int id) {
        return noteRepo.findById(id).orElse(null);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepo.findAll();
    }

    @Override
    public void deleteNote(int id) {
        if (noteRepo.existsById(id)) {
            noteRepo.deleteById(id);
        }


    }

    @Override
    public Note updateNote(Note note) {
        if (noteRepo.existsById(note.getId_note())) {
            return noteRepo.save(note);
        }
        return null ;
    }
    @Override
    public Note affectNoteToEtudiant(Integer etudiantId, Integer examId, Float valeur)  {
        Optional<Etudiant> etudiantOpt = etudiantRepo.findById(etudiantId);
        if (etudiantOpt.isEmpty()) {
            throw new IllegalArgumentException("Etudiant not found with id: " + etudiantId);
        }

        Etudiant etudiant = etudiantOpt.get();

        Note note = new Note();
        note.setEtudiant(etudiant);
        note.setId_examen(examId);
        note.setValeur(valeur);

        Note savedNote = noteRepo.save(note);

        Mail mail = Mail.builder()
                .from("aboussaoudnour436@gmail.com")
                .mailTo(etudiant.getEmail())
                .subject("Nouvelle note affectée")
                .body("Bonjour " + etudiant.getPrenom() + ",\n\nUne note de " + valeur + " a été affectée pour l'examen ID: " + examId + ".\n\nCordialement.")
                .build();

        try {
            emailSenderServiceIMP.sendEmail(mail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return savedNote;
    }
}
