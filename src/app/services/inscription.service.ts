import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Inscription } from '../models/inscription.model';

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {
  private apiUrl = 'http://localhost:8093/inscriptions';

  constructor(private http: HttpClient) {}

  getAllInscriptions(): Observable<Inscription[]> {
    return this.http.get<Inscription[]>(`${this.apiUrl}/getAll`);
  }

  getInscriptionById(id: number): Observable<Inscription> {
    return this.http.get<Inscription>(`${this.apiUrl}/getById/${id}`);
  }

  addInscription(inscription: Inscription): Observable<Inscription> {
    return this.http.post<Inscription>(`${this.apiUrl}/addInscription`, inscription);
  }

  updateInscription(id: number, inscription: Inscription): Observable<Inscription> {
    return this.http.put<Inscription>(`${this.apiUrl}/update/${id}`, inscription);
  }

  deleteInscription(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}