import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalleDetailComponent } from './salle-detail.component';

describe('SalleDetailComponent', () => {
  let component: SalleDetailComponent;
  let fixture: ComponentFixture<SalleDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SalleDetailComponent]
    });
    fixture = TestBed.createComponent(SalleDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
