import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenAppointmentsComponent } from './open-appointments.component';

describe('OpenAppointmentsComponent', () => {
  let component: OpenAppointmentsComponent;
  let fixture: ComponentFixture<OpenAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OpenAppointmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
