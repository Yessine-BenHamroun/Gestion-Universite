import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { Holiday } from '../models/examen.model';

@Injectable({
  providedIn: 'root'
})
export class HolidayService {
  constructor(private api: ApiService) { }

  getHolidays(year: number, country: string = 'MA'): Observable<Holiday[]> {
    return this.api.get(`salles/jours-feries?year=${year}&country=${country}`);
  }
}