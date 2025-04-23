import { Etudiant } from "./etudiant.model";

export interface Inscription {
  id_inscription?: number;
  etudiant?: Etudiant;
  id_cours: number;
  annee_academique: string;
}