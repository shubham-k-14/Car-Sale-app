import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerAdminOperationsComponent } from './customer-admin-operations.component';

describe('CustomerAdminOperationsComponent', () => {
  let component: CustomerAdminOperationsComponent;
  let fixture: ComponentFixture<CustomerAdminOperationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerAdminOperationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerAdminOperationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
