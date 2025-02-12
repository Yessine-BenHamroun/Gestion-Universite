package com.esprit.microservice.etudiant.controller;

import com.esprit.microservice.etudiant.models.Note;
import com.esprit.microservice.etudiant.service.INService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAllNotes")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }
}