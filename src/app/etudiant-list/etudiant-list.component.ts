// etudiant-list.component.ts
import { Component, OnInit } from '@angular/core';
import { EtudiantService } from '../services/etudiant.service';
import { InscriptionService } from '../services/inscription.service';
import { NoteService } from '../services/note.service';
import { Etudiant } from '../models/etudiant.model';
import { Inscription } from '../models/inscription.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-etudiant-list',
  templateUrl: './etudiant-list.component.html',
  styleUrls: ['./etudiant-list.component.css']
})
export class EtudiantListComponent implements OnInit {
  etudiants: Etudiant[] = [];
  allInscriptions: Inscription[] = [];
  selectedEtudiant: Etudiant | null = null;
  showInscriptions = false;
  showAddEtudiant = false;
  showAffectNote = false;
  showAffectInscription = false;
  
  etudiantForm: FormGroup;
  noteForm: FormGroup;
  inscriptionForm: FormGroup;

  constructor(
    private etudiantService: EtudiantService,
    private inscriptionService: InscriptionService,
    private noteService: NoteService,
    private fb: FormBuilder
  ) {
    this.etudiantForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      date_naissance: ['', Validators.required],
      adresse: ['', Validators.required],
      telephone: ['', Validators.required],
      niveau: ['', Validators.required],
      id_departement: [null, Validators.required]
    });

    this.noteForm = this.fb.group({
      id_examen: [null, Validators.required],
      valeur: [null, [Validators.required, Validators.min(0), Validators.max(20)]]
    });

    this.inscriptionForm = this.fb.group({
      id_inscription: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadEtudiants();
    this.loadInscriptions();
  }

  loadEtudiants(): void {
    this.etudiantService.getAllEtudiants().subscribe({
      next: (data) => {
        this.etudiants = data;
      },
      error: (error) => {
        console.error('Error loading students:', error);
      }
    });
  }

  loadInscriptions(): void {
    this.inscriptionService.getAllInscriptions().subscribe({
      next: (data) => {
        this.allInscriptions = data;
      },
      error: (error) => {
        console.error('Error loading inscriptions:', error);
      }
    });
  }

  onSelectEtudiant(etudiant: Etudiant): void {
    // Store a clean reference first
    this.selectedEtudiant = {...etudiant};
    this.showInscriptions = true;
    
    // Refresh etudiant data to get updated inscriptions
    this.etudiantService.getEtudiantById(etudiant.id_etudiant!).subscribe({
      next: (data) => {
        if (data) {
          // Ensure inscriptions is defined
          if (!data.inscriptions) {
            data.inscriptions = [];
          }
          this.selectedEtudiant = data;
        }
      },
      error: (error) => {
        console.error('Error fetching student details:', error);
      }
    });
  }

  toggleAddEtudiant(): void {
    this.showAddEtudiant = !this.showAddEtudiant;
    if (this.showAddEtudiant) {
      this.etudiantForm.reset();
    }
  }

  toggleAffectNote(etudiant: Etudiant): void {
    this.selectedEtudiant = {...etudiant};
    this.showAffectNote = !this.showAffectNote;
    if (this.showAffectNote) {
      this.noteForm.reset();
    }
  }

  toggleAffectInscription(etudiant: Etudiant): void {
    this.selectedEtudiant = {...etudiant};
    this.showAffectInscription = !this.showAffectInscription;
    if (this.showAffectInscription) {
      this.inscriptionForm.reset();
    }
  }

  onSubmitEtudiant(): void {
    if (this.etudiantForm.valid) {
      // Make a clean copy of the form value to avoid any reference issues
      const etudiant = {...this.etudiantForm.value};
      
      // Parse date properly if needed
      if (typeof etudiant.date_naissance === 'string') {
        etudiant.date_naissance = new Date(etudiant.date_naissance);
      }
      
      this.etudiantService.addEtudiant(etudiant).subscribe({
        next: () => {
          this.loadEtudiants();
          this.showAddEtudiant = false;
          this.etudiantForm.reset();
        },
        error: (error) => {
          console.error('Error adding student:', error);
        }
      });
    }
  }

  onSubmitNote(): void {
    if (this.noteForm.valid && this.selectedEtudiant) {
      const { id_examen, valeur } = this.noteForm.value;
      this.noteService.affectNote(this.selectedEtudiant.id_etudiant!, id_examen, valeur).subscribe({
        next: () => {
          this.showAffectNote = false;
          this.noteForm.reset();
          alert('Note ajoutée avec succès');
        },
        error: (error) => {
          console.error('Error affecting note:', error);
        }
      });
    }
  }

  onSubmitInscription(): void {
    if (this.inscriptionForm.valid && this.selectedEtudiant) {
      const inscriptionId = this.inscriptionForm.value.id_inscription;
      this.etudiantService.assignInscriptionToEtudiant(this.selectedEtudiant.id_etudiant!, inscriptionId).subscribe({
        next: () => {
          this.showAffectInscription = false;
          this.inscriptionForm.reset();
          // Refresh student details after assigning inscription
          if (this.selectedEtudiant) {
            this.onSelectEtudiant(this.selectedEtudiant);
          }
        },
        error: (error) => {
          console.error('Error affecting inscription:', error);
        }
      });
    }
  }

  desaffecterInscription(inscriptionId: number): void {
    if (this.selectedEtudiant) {
      this.etudiantService.desaffecterInscription(this.selectedEtudiant.id_etudiant!, inscriptionId).subscribe({
        next: () => {
          // Refresh student details after removing inscription
          if (this.selectedEtudiant) {
            this.onSelectEtudiant(this.selectedEtudiant);
          }
        },
        error: (error) => {
          console.error('Error removing inscription:', error);
        }
      });
    }
  }

  deleteEtudiant(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cet étudiant?')) {
      this.etudiantService.deleteEtudiant(id).subscribe({
        next: () => {
          this.loadEtudiants();
          if (this.selectedEtudiant?.id_etudiant === id) {
            this.selectedEtudiant = null;
            this.showInscriptions = false;
          }
        },
        error: (error) => {
          console.error('Error deleting student:', error);
        }
      });
    }
  }

  getAvailableInscriptions(): Inscription[] {
    if (!this.selectedEtudiant || !this.selectedEtudiant.inscriptions) {
      return this.allInscriptions;
    }
    
    const existingIds = this.selectedEtudiant.inscriptions.map(i => i.id_inscription);
    return this.allInscriptions.filter(i => !existingIds.includes(i.id_inscription));
  }
}