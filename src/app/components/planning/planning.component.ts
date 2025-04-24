import { Component, OnInit } from '@angular/core';
import { Examen } from '../../models/examen.model';
import { Salle } from '../../models/salle.model';
import { ExamenService } from '../../services/examen.service';
import { SalleService } from '../../services/salle.service';
import { DatePipe } from '@angular/common';
import { forkJoin } from 'rxjs';

interface DisplayExamen {
  id: number;
  dateExamen: string;
  typeExamen: string;
  coefficient: number;
  nbEtudiants: number;
  nomExamen: string;
  salle?: {
    nomSalle: string;
  };
}

@Component({
  selector: 'app-planning',
  templateUrl: './planning.component.html',
  styleUrls: ['./planning.component.css'],
  providers: [DatePipe]
})
export class PlanningComponent implements OnInit {
  examens: Examen[] = [];
  salles: Salle[] = [];
  selectedDate: Date = new Date();
  isLoading: boolean = true;

  constructor(
    private examenService: ExamenService,
    private salleService: SalleService,
    private datePipe: DatePipe
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.isLoading = true;
    
    forkJoin([
      this.examenService.getAllExamens(),
      this.salleService.getAllSalles()
    ]).subscribe({
      next: ([examens, salles]) => {
        this.examens = examens;
        this.salles = salles;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Erreur:', err);
        this.isLoading = false;
      }
    });
  }

  changeDate(days: number): void {
    const newDate = new Date(this.selectedDate);
    newDate.setDate(newDate.getDate() + days);
    this.selectedDate = newDate;
  }

  getExamensForDate(date: Date): DisplayExamen[] {
    return this.examens.filter(examen => {
      try {
        const dateStr = examen.dateExamen.toString().replace(/\.000:/, '.000+');
        const examDate = new Date(dateStr);
        return examDate.getFullYear() === date.getFullYear() &&
               examDate.getMonth() === date.getMonth() &&
               examDate.getDate() === date.getDate();
      } catch {
        return false;
      }
    }).map(examen => {
      const assignedSalle = this.salles.find(s => 
        s.examenList?.some(e => e.id === examen.id)
      );

      return {
        ...examen,
        dateExamen: examen.dateExamen.toString(),
        nomExamen: (examen as any).nomExamen || examen.typeExamen,
        salle: assignedSalle ? { 
          nomSalle: this.getSalleName(assignedSalle.id) 
        } : undefined
      };
    });
  }

  formatExamTime(dateString: string): string {
    try {
      const date = new Date(dateString.replace(/\.000:/, '.000+'));
      return this.datePipe.transform(date, 'HH:mm') || '00:00';
    } catch {
      return 'Heure invalide';
    }
  }

  getSalleName(salleId: number): string {
    const salle = this.salles.find(s => s.id === salleId);
    return salle ? salle.nomSalle : 'Non affecté';
  }
}