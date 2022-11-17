import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';

import { Address } from '../models/address.entity';
import { Customer } from '../models/customer.entity';
import { CustomerService } from '../service/customer.service';
import { SignUpComponent } from '../sign-up/sign-up.component';
import { Observable } from 'rxjs';
import { ajax } from 'rxjs/ajax';



@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
})
export class CustomerComponent implements OnInit {
  id!: any;

  customer: Customer = {
    role: '',
    password: '',
    name: '',
    email: '',
    contactNo: 0,
    dob: new Date(),
    address: [
      { pincode: 0, doorNo: '', street: '', area: '', city: '', state: '' },
    ],
  };
  @Input() user: any;
  ngOnInit(): void {
    console.log(this.user);
  }

  customerForm = this.fb.group({
    name: [
      '',
      [
        Validators.required,
        Validators.minLength(5),
        Validators.pattern('[a-z A-Z]{5,20}'),
      ],
    ],
    email: ['', [Validators.email, Validators.required]],
    contactNo: [
      '',
      [Validators.required, Validators.pattern('[9876][0-9]{9}')],
    ],
    dob: ['', [Validators.required]],
    address: this.fb.group({
      zip: ['', [Validators.required]],
      doorNo: ['', [Validators.required]],
      street: ['', [Validators.required]],
      area: ['', [Validators.required]],
      city: ['', [Validators.required]],
      state: ['', [Validators.required]],
    }),
  });

  constructor(
    private custService: CustomerService,
    private fb: FormBuilder,
    private s: SignUpComponent,
    private _route: Router
  ) {}

  onSubmit() {
    this.customer = {
      role: this.user.role,
      password: this.user.password,
      name: this.customerForm.controls['name'].value,
      email: this.customerForm.controls['email'].value,
      contactNo: this.customerForm.controls['contactNo'].value,
      dob: this.customerForm.controls['dob'].value,
      address: [
        {
          pincode: this.customerForm.get('address.zip')?.value,
          doorNo: this.customerForm.get('address.doorNo')?.value,
          street: this.customerForm.get('address.street')?.value,
          area: this.customerForm.get('address.area')?.value,
          city: this.customerForm.get('address.city')?.value,
          state: this.customerForm.get('address.state')?.value,
        },
      ],
    };
    console.log(this.customer);

    let resp = this.custService.addCustomer(this.customer);
    resp.subscribe((data: any) => {
      this.id = data.userId;
      localStorage.setItem('userIdKey', this.id);
      console.log(this.id);
    });
    console.log(localStorage.getItem('userIdKey'));
  }

  public customers: any = [];
  getAllCustomers() {
    let resp = this.custService
      .getAllCustomers()
      .subscribe((data) => (this.customers = data));
  }

  get name() {
    return this.customerForm.controls['name'];
  }

  get email() {
    return this.customerForm.controls['email'];
  }

  get contactNo() {
    return this.customerForm.controls['contactNo'];
  }

  get zip() {
    return this.customerForm.controls['zip'];
  }

  get dob() {
    return this.customerForm.controls['dob'];
  }

  public dobYear: any;

  validateDate(): any {
    let currYear: any = new Date().getFullYear();
    console.log(currYear);
    let dob = this.dob.value.split('-');
    let dobYear = parseInt(dob[0]);
    console.log(dobYear);
    if (currYear - dobYear < 18) alert('Age should be greater than 18');
  }
}
