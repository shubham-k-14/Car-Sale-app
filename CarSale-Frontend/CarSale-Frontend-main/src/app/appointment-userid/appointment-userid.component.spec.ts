import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentUseridComponent } from './appointment-userid.component';

describe('AppointmentUseridComponent', () => {
  let component: AppointmentUseridComponent;
  let fixture: ComponentFixture<AppointmentUseridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentUseridComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentUseridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
