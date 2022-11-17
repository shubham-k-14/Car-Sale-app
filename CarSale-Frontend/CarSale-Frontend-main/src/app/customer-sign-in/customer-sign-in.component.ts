import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../models/customer.entity';
import { CustomerService } from '../service/customer.service';

@Component({
  selector: 'app-customer-sign-in',
  templateUrl: './customer-sign-in.component.html',
  styleUrls: ['./customer-sign-in.component.css']
})
export class CustomerSignInComponent implements OnInit {

  constructor(private custService:CustomerService,private _route:ActivatedRoute,private _routes:Router) { }

  id:number=1;

  ngOnInit(): void {
    this.id = this._route.snapshot.params['id'];
  }

  customer:any={}
  

  viewProfile(){
      this._routes.navigate(['/customer-profile']);
    }
  
  }

