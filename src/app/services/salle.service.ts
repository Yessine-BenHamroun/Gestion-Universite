import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { Salle, SalleStatusDTO } from '../models/salle.model';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class SalleService {
  constructor(private api: ApiService) { }

  getAllSalles(): Observable<Salle[]> {
    return this.api.get<Salle[]>('salles/salles').pipe(
      map((response: any) => {
        // Correction du format de date si nécessaire
        return response.map((salle: any) => ({
          ...salle,
          examenList: salle.examenList?.map((examen: any) => ({
            ...examen,
            dateExamen: this.fixDateFormat(examen.dateExamen)
          }))
        }));
      })
    );
  }

  private fixDateFormat(dateString: string): Date {
    // Logique pour corriger le format de date
    // Exemple basique - à adapter selon votre format réel
    return new Date(dateString.replace(/(\d{4}-\d{2}-\d{2})/, '$1T'));
  }


  getSalleById(id: number): Observable<Salle> {
    return this.api.get(`salles/salle/${id}`);
  }

  createSalle(salle: Salle): Observable<Salle> {
    return this.api.post('salles/ajouter', salle);
  }

  updateSalle(id: number, salle: Salle): Observable<Salle> {
    return this.api.put(`salles/updates/${id}`, salle);
  }

  deleteSalle(id: number): Observable<void> {
    return this.api.delete(`salles/deletes/${id}`);
  }

  getSallesStatus(): Observable<SalleStatusDTO[]> {
    return this.api.get('salles/salles/status');
  }
}