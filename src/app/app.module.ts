import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SalleListComponent } from './components/salle-list/salle-list.component';
import { SalleFormComponent } from './components/salle-form/salle-form.component';
import { SalleDetailComponent } from './components/salle-detail/salle-detail.component';
import { ExamenListComponent } from './components/examen-list/examen-list.component';
import { ExamenFormComponent } from './components/examen-form/examen-form.component';
import { ExamenDetailComponent } from './components/examen-detail/examen-detail.component';
import { PlanningComponent } from './components/planning/planning.component';
import { HolidaysComponent } from './components/holidays/holidays.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import localeFrExtra from '@angular/common/locales/extra/fr';
import { CommonModule } from '@angular/common';

registerLocaleData(localeFr, 'fr', localeFrExtra);
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SalleListComponent,
    SalleFormComponent,
    SalleDetailComponent,
    ExamenListComponent,
    ExamenFormComponent,
    ExamenDetailComponent,
    PlanningComponent,
   
    HolidaysComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    
    CommonModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
