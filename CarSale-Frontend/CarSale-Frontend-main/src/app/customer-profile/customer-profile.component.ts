import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../models/customer.entity';
import { CustomerService } from '../service/customer.service';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css'],
})
export class CustomerProfileComponent implements OnInit {
  constructor(
    private custService: CustomerService,
    private _route: ActivatedRoute,
    private _routes: Router
  ) {}

  id!: number;

  customer!: Customer;
  ngOnInit(): void {
    this.id = <number>(<any>localStorage.getItem('userId'));
    let resp1 = this.custService.getCustomerById(this.id).subscribe(
      (data) => {
        console.log(data);
        this.customer = data;
      },
      (error) => {
        console.log(error);
        alert(error);
      }
    );
  }

  updateCustomer(id: number) {
    this._routes.navigate(['update-customer', id]);
  }

  removeCustomer(id: number) {
    let r = confirm('Press a button!');
    if (r == true) {
      this.custService.removeCustomer(id).subscribe((data) => {
        data;
      });
      this._routes.navigate(['delete-customer', id]);
    }
  }
}
