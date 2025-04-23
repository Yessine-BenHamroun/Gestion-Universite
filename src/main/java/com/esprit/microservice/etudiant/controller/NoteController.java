package com.esprit.microservice.etudiant.controller;

import com.esprit.microservice.etudiant.models.Note;
import com.esprit.microservice.etudiant.service.INService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private INService noteService;

    @PostMapping("/addNote")
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    @GetMapping("/getNoteById/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable int id) {
        Note note = noteService.getNoteById(id);
        return (note != null) ? ResponseEntity.ok(note) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteNote/{id}")
    public void deleteNoteById(@PathVariable int id) {
        noteService.deleteNote(id);
    }

    @PutMapping("/updateNote/{id}")
    public Note updateNoteById(@PathVariable int id, @RequestBody Note updatedNote) {
        updatedNote.setId_note(id);
        return noteService.updateNote(updatedNote);
    }

    // testeha hekka PUT /api/notes/affect?etudiantId=1&examId=101&valeur=16.5
    @GetMapping("/getAllNotes")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PutMapping("/affect")
    public ResponseEntity<?> affectNote(
            @RequestParam Integer etudiantId,
            @RequestParam Integer examId,
            @RequestParam Float valeur
    ) {
        try {
            Note note = noteService.affectNoteToEtudiant(etudiantId, examId, valeur);
            return ResponseEntity.ok(note);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}