import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ExamenService } from '../../services/examen.service';
import { Examen, TypeExamen } from '../../models/examen.model';
import { SalleService } from '../../services/salle.service';
import { Salle } from '../../models/salle.model';

@Component({
  selector: 'app-examen-form',
  templateUrl: './examen-form.component.html',
  styleUrls: ['./examen-form.component.css']
})
export class ExamenFormComponent implements OnInit {
  examenForm: FormGroup;
  isEditMode = false;
  examenId?: number;
  salles: Salle[] = [];
  typeExamenOptions = Object.values(TypeExamen);

  constructor(
    private fb: FormBuilder,
    private examenService: ExamenService,
    private salleService: SalleService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.examenForm = this.fb.group({
      dateExamen: ['', Validators.required],
      typeExamen: ['', Validators.required],
      coefficient: ['', [Validators.required, Validators.min(0)]],
      nbEtudiants: ['', [Validators.required, Validators.min(1)]],
      salleId: [null]
    });
  }

  ngOnInit(): void {
    this.loadSalles();
    
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.examenId = +params['id'];
        this.loadExamen(this.examenId);
      }
    });
  }

  loadExamen(id: number): void {
    this.examenService.getExamenById(id).subscribe(examen => {
      this.examenForm.patchValue({
        ...examen,
        salleId: examen.salle?.id
      });
    });
  }

  loadSalles(): void {
    this.salleService.getAllSalles().subscribe(salles => {
      this.salles = salles;
    });
  }

  onSubmit(): void {
    if (this.examenForm.valid) {
      const examenData = {
        ...this.examenForm.value,
        id: this.examenId
      };

      const operation = this.isEditMode
        ? this.examenService.updateExamen(this.examenId!, examenData)
        : this.examenService.createExamen(examenData);

      operation.subscribe({
        next: () => {
          this.router.navigate(['/examens']);
        },
        error: (err) => {
          console.error('Erreur:', err);
        }
      });
    }
  }
}