import { Etudiant } from "./etudiant.model";

export interface Note {
  id_note?: number;
  valeur: number;
  etudiant?: Etudiant;
  id_examen: number;
}