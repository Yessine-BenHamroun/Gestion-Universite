import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Note } from '../models/note.model';

@Injectable({
  providedIn: 'root'
})
export class NoteService {
  private apiUrl = 'http://localhost:8093/notes';

  constructor(private http: HttpClient) {}

  getAllNotes(): Observable<Note[]> {
    return this.http.get<Note[]>(`${this.apiUrl}/getAllNotes`);
  }

  getNoteById(id: number): Observable<Note> {
    return this.http.get<Note>(`${this.apiUrl}/getNoteById/${id}`);
  }

  addNote(note: Note): Observable<Note> {
    return this.http.post<Note>(`${this.apiUrl}/addNote`, note);
  }

  updateNote(id: number, note: Note): Observable<Note> {
    return this.http.put<Note>(`${this.apiUrl}/updateNote/${id}`, note);
  }

  deleteNote(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/deleteNote/${id}`);
  }

  affectNote(etudiantId: number, examId: number, valeur: number): Observable<Note> {
    return this.http.put<Note>(`${this.apiUrl}/affect?etudiantId=${etudiantId}&examId=${examId}&valeur=${valeur}`, {});
  }
}