import { Examen, TypeExamen } from './examen.model';
export interface Salle {
    id: number;
    nomSalle: string;
    capacite: number;
    typeSalle: TypeSalle;
    examenList?: Examen[];
  }
  
  export enum TypeSalle {
    AMPHI = 'AMPHI',
    SALLE_TP = 'SALLE_TP',
    SALLE_COURS = 'SALLE_COURS'
  }
  
  export interface SalleStatusDTO {
    id: number;
    nomSalle: string;
    capacite: number;
    typeSalle: TypeSalle;
    examenList?: ExamenDTO[];
  }
  
  export interface ExamenDTO {
    id: number;
    dateExamen: Date;
    typeExamen: TypeExamen;
    coefficient: number;
    nbEtudiants: number;
  }