import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SalleService } from '../../services/salle.service';
import { Salle, TypeSalle } from '../../models/salle.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-salle-form',
  templateUrl: './salle-form.component.html',
  styleUrls: ['./salle-form.component.css']
})
export class SalleFormComponent implements OnInit {
  salleForm: FormGroup;
  isEditMode = false;
  salleId?: number;
  typeSalleOptions = Object.values(TypeSalle);

  constructor(
    private fb: FormBuilder,
    private salleService: SalleService,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.salleForm = this.fb.group({
      nomSalle: ['', [Validators.required, Validators.maxLength(50)]],
      capacite: ['', [Validators.required, Validators.min(1)]],
      typeSalle: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.salleId = +params['id'];
        this.loadSalle(this.salleId);
      }
    });
  }

  loadSalle(id: number): void {
    this.salleService.getSalleById(id).subscribe({
      next: (salle) => {
        this.salleForm.patchValue(salle);
      },
      error: (err) => {
        this.toastr.error('Erreur lors du chargement de la salle');
        console.error('Erreur:', err);
      }
    });
  }

  onSubmit(): void {
    if (this.salleForm.valid) {
      const salleData = {
        ...this.salleForm.value,
        id: this.salleId
      };
  
      const operation = this.isEditMode
        ? this.salleService.updateSalle(this.salleId!, salleData)
        : this.salleService.createSalle(salleData);
  
      operation.subscribe({
        next: () => {
          this.toastr.success(
            `Salle ${this.isEditMode ? 'modifiée' : 'créée'} avec succès`,
            'Succès',
            { timeOut: 3000 }
          );
          this.router.navigate(['/salles']);
        },
        error: (err) => {
          this.toastr.error(
            'Erreur lors de la sauvegarde',
            'Erreur',
            { timeOut: 3000 }
          );
          console.error('Erreur:', err);
        }
      });
    }
  }
}