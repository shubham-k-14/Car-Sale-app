import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../models/customer.entity';
import { CustomerService } from '../service/customer.service';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {

 id:number=0;
 customer!:Customer

  constructor(private custService:CustomerService,private _route:ActivatedRoute,private _routes:Router) { }

  ngOnInit(): void {
    this.id=this._route.snapshot.params['id'];
    this.custService.getCustomerById(this.id).subscribe(data=>{
      this.customer=data;
      console.log(data);  
    },error=>console.log(error)
    )
  }


  onSubmit(){
      this.custService.updateCustomer(this.id,this.customer).subscribe(data=>{
        alert("customer updated")
        this._routes.navigate(['/customer-profile'])
      })
  }

}
