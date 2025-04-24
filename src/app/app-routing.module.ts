import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SalleListComponent } from './components/salle-list/salle-list.component';
import { ExamenListComponent } from './components/examen-list/examen-list.component';
import { SalleFormComponent } from './components/salle-form/salle-form.component';
import { ExamenFormComponent } from './components/examen-form/examen-form.component';
import { SalleDetailComponent } from './components/salle-detail/salle-detail.component';
import { ExamenDetailComponent } from './components/examen-detail/examen-detail.component';
import { PlanningComponent } from './components/planning/planning.component';

import { HolidaysComponent } from './components/holidays/holidays.component';

const routes: Routes = [
  { path: 'salles', component: SalleListComponent },
  { path: 'salles/new', component: SalleFormComponent },
  { path: 'salles/:id', component: SalleDetailComponent },
  { path: 'salles/:id/edit', component: SalleFormComponent },
  { path: 'examens', component: ExamenListComponent },
  { path: 'examens/new', component: ExamenFormComponent },
  { path: 'examens/:id', component: ExamenDetailComponent },
  { path: 'examens/:id/edit', component: ExamenFormComponent },
  { path: 'planning', component: PlanningComponent },

  { path: 'holidays', component: HolidaysComponent },
  { path: '', redirectTo: '/salles', pathMatch: 'full' },
  { path: '**', redirectTo: '/salles' },
  { path: 'examens', component: ExamenListComponent },
  { path: 'examens/new', component: ExamenFormComponent },
  { path: 'examens/:id/edit', component: ExamenFormComponent },
  { path: 'salles', component: SalleListComponent },
  { path: 'salles/new', component: SalleFormComponent },
  { path: 'salles/:id/edit', component: SalleFormComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }