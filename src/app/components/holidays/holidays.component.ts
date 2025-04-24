import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Holiday {
  name: string;
  date: string;
  description: string;
}

@Component({
  selector: 'app-holidays',
  templateUrl: './holidays.component.html',
  styleUrls: ['./holidays.component.css']
})
export class HolidaysComponent implements OnInit {
  holidays: Holiday[] = [];
  year: number = new Date().getFullYear();
  country: string = 'MA'; // Par défaut Maroc
  loading: boolean = false;
  error: string | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchHolidays();
  }

  fetchHolidays(): void {
    this.loading = true;
    this.error = null;
    const apiKey = 'qjazyeKjc9ezGR977U3syRm0JBfpNPfQ';
    
    this.http.get<any>(`https://calendarific.com/api/v2/holidays`, {
      params: {
        api_key: apiKey,
        country: this.country,
        year: this.year.toString()
      }
    }).subscribe({
      next: (response) => {
        if (response.response && response.response.holidays) {
          this.holidays = response.response.holidays.map((h: any) => ({
            name: h.name,
            date: h.date.iso,
            description: h.description || 'Aucune description disponible'
          }));
        }
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des jours fériés';
        this.loading = false;
        console.error(err);
      }
    });
  }

  onYearChange(newYear: number): void {
    this.year = newYear;
    this.fetchHolidays();
  }

  onCountryChange(newCountry: string): void {
    this.country = newCountry;
    this.fetchHolidays();
  }
}