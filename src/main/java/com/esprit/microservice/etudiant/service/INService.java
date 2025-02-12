package com.esprit.microservice.etudiant.service;

import com.esprit.microservice.etudiant.models.Note;

import java.util.List;

public interface INService {
    Note addNote (Note note);
    Note getNoteById (int id);
    List<Note> getAllNotes ();
    void deleteNote (int id);
    Note updateNote (Note note);

}
