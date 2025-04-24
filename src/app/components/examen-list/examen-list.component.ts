import { Component, OnInit } from '@angular/core';
import { Examen } from '../../models/examen.model';
import { ExamenService } from '../../services/examen.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-examen-list',
  templateUrl: './examen-list.component.html',
  styleUrls: ['./examen-list.component.css']
})
export class ExamenListComponent implements OnInit {
  examens: Examen[] = [];
  isLoading: boolean = true;
  errorMessage: string | null = null;

  constructor(  private examenService: ExamenService,
    private router: Router) { }

  ngOnInit(): void {
    this.loadExamens();
  }

  loadExamens(): void {
    this.isLoading = true;
    this.errorMessage = null;
    
    this.examenService.getAllExamens().subscribe({
      next: (data) => {
        this.examens = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Erreur:', err);
        this.errorMessage = 'Erreur lors du chargement des examens';
        this.isLoading = false;
      }
    });
  }

  deleteExamen(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cet examen ?')) {
      this.examenService.deleteExamen(id).subscribe({
        next: () => {
          this.examens = this.examens.filter(e => e.id !== id);
        },
        error: (err) => {
          console.error('Erreur lors de la suppression', err);
        }
      });
    }
  }editExamen(id: number): void {
    this.router.navigate(['/examens', id, 'edit']);
  }}