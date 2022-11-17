import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../models/customer.entity';
import { CustomerService } from '../service/customer.service';

@Component({
  selector: 'app-customer-admin-operations',
  templateUrl: './customer-admin-operations.component.html',
  styleUrls: ['./customer-admin-operations.component.css']
})
export class CustomerAdminOperationsComponent implements OnInit {

  allCustomers:Customer[] = [];
  
  constructor(private custService:CustomerService,private _route:ActivatedRoute,private _routes:Router) { 
  }

  ngOnInit(): void {
    this.getAllCustomers();
    // // this.city=this._route.snapshot.params['city'];
    // this.getCustomersByLocations();
  }
  
  isError:boolean=false;

  public getAllCustomers(){
      this.custService.getAllCustomers().subscribe(data=>{
        this.allCustomers=data;
      },error=>{
        this.isError=true;
        console.log(error)}
      )
  }

  flag:number=0;

  showTable(){
    this.flag=1;
  }

  closeTable(){
    this.flag=0;
  }

  city:string="";

  onClick(){
   this.city=(<HTMLInputElement>document.getElementById("cityName")).value;
   console.log(this.city);
   this.getCustomersByLocations();
  }
  
  
  allCustomersByLocation:Customer[]=[];

  public getCustomersByLocations(){
      this.custService.getCustomerByLocation(this.city).subscribe(data=>{
        console.log(data);
        this.allCustomersByLocation=data})
  }

  
}
