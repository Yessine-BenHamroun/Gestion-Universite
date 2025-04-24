import { Salle } from './salle.model';

export interface Examen {
  id: number;
  dateExamen: Date;
  typeExamen: TypeExamen;
  coefficient: number;
  nbEtudiants: number;
  salle?: Salle;
}

export enum TypeExamen {
  CONTROLE_CONTINU = 'CONTROLE_CONTINU',
  EXAMEN_FINAL = 'EXAMEN_FINAL'
}

export interface Holiday {
  name: string;
  date: Date;
}