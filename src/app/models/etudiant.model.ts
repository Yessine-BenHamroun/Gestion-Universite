import { Inscription } from "./inscription.model";

export interface Etudiant {
  id_etudiant?: number;
  nom: string;
  prenom: string;
  email: string;
  date_naissance: Date;
  adresse: string;
  telephone: string;
  niveau: string;
  id_departement: number;
  inscriptions?: Inscription[];
}