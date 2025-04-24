import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalleListComponent } from './salle-list.component';

describe('SalleListComponent', () => {
  let component: SalleListComponent;
  let fixture: ComponentFixture<SalleListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SalleListComponent]
    });
    fixture = TestBed.createComponent(SalleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
