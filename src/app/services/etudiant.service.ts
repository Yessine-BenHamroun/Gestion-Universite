import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Etudiant } from '../models/etudiant.model';
import { Inscription } from '../models/inscription.model';

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {
  private apiUrl = 'http://localhost:8093/etudiants';
  
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {}

  getAllEtudiants(): Observable<any[]> {
    return this.http.get<Etudiant[]>(`${this.apiUrl}/getAll`);
  }

  getEtudiantById(id: number): Observable<Etudiant> {
    return this.http.get<Etudiant>(`${this.apiUrl}/getById/${id}`);
  }

  addEtudiant(etudiant: Etudiant): Observable<Etudiant> {
    return this.http.post<Etudiant>(`${this.apiUrl}/add`, etudiant, this.httpOptions);
  }

  updateEtudiant(id: number, etudiant: Etudiant): Observable<Etudiant> {
    return this.http.put<Etudiant>(`${this.apiUrl}/update/${id}`, etudiant, this.httpOptions);
  }

  deleteEtudiant(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  assignInscriptionToEtudiant(etudiantId: number, inscriptionId: number): Observable<Inscription> {
    return this.http.put<Inscription>(
      `${this.apiUrl}/${etudiantId}/inscriptions/${inscriptionId}/assign`, 
      {}, 
      this.httpOptions
    );
  }

  desaffecterInscription(etudiantId: number, inscriptionId: number): Observable<Inscription> {
    return this.http.put<Inscription>(
      `${this.apiUrl}/desaffect-inscription/${inscriptionId}/from-etudiant/${etudiantId}`, 
      {}, 
      this.httpOptions
    );
  }
}