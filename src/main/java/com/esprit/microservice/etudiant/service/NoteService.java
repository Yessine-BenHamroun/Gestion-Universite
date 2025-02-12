package com.esprit.microservice.etudiant.service;

import com.esprit.microservice.etudiant.models.Note;
import com.esprit.microservice.etudiant.repository.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements INService{
    @Autowired
    private NoteRepo noteRepo;

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
}
