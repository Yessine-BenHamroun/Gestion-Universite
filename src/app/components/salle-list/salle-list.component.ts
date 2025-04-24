import { Component, OnInit } from '@angular/core';
import { Salle } from '../../models/salle.model';
import { SalleService } from '../../services/salle.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-salle-list',
  templateUrl: './salle-list.component.html',
  styleUrls: ['./salle-list.component.css']
})
export class SalleListComponent implements OnInit {
  salles: Salle[] = [];
  isLoading: boolean = true;
  errorMessage: string | null = null;

  constructor(
    private salleService: SalleService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.loadSalles();
  }

  loadSalles(): void {
    this.isLoading = true;
    this.errorMessage = null;
    
    this.salleService.getAllSalles().subscribe({
      next: (data) => {
        this.salles = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Erreur:', err);
        this.errorMessage = 'Erreur lors du chargement des salles';
        this.isLoading = false;
      }
    });
  }editSalle(id: number): void {
    this.router.navigate(['/salles', id, 'edit']);
  }
  
  deleteSalle(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette salle ?')) {
      this.salleService.deleteSalle(id).subscribe({
        next: () => {
          this.salles = this.salles.filter(s => s.id !== id);
          this.toastr.success('Salle supprimée avec succès');
        },
        error: (err) => {
          this.toastr.error('Erreur lors de la suppression');
          console.error('Erreur:', err);
        }
      });
    }
  }
}