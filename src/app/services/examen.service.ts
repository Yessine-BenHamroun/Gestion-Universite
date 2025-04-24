import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { Examen } from '../models/examen.model';

@Injectable({
  providedIn: 'root'
})
export class ExamenService {
  constructor(private api: ApiService) { }

  getAllExamens(): Observable<Examen[]> {
    return this.api.get('salles/exams');
  }

  getExamenById(id: number): Observable<Examen> {
    return this.api.get(`salles/exaMm/${id}`);
  }

  createExamen(examen: Examen): Observable<Examen> {
    return this.api.post('salles/exam', examen);
  }

  updateExamen(id: number, examen: Examen): Observable<Examen> {
    return this.api.put(`salles/examen/${id}`, examen);
  }

  deleteExamen(id: number): Observable<void> {
    return this.api.delete(`salles/delete/${id}`);
  }

  affecterExamenASalle(examenId: number, salleId: number): Observable<any> {
    return this.api.put(`salles/examens/${examenId}/salle/${salleId}`, {});
  }

  planifierExamenAuto(examen: Examen): Observable<Examen> {
    return this.api.post('salles/examens/auto', examen);
  }

  desaffecterExamen(examenId: number): Observable<Examen> {
    return this.api.put(`salles/examens/${examenId}/desaffecter`, {});
  }

  getExamensBySalle(salleId: number): Observable<Examen[]> {
    return this.api.get(`salles/examm/${salleId}`);
  }
}