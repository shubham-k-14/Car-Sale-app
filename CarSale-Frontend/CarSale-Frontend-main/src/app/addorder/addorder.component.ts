import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../order';
import { OrderServiceService } from '../order-service.service';

@Component({
  selector: 'app-addorder',
  templateUrl: './addorder.component.html',
  styleUrls: ['./addorder.component.css']
})
export class AddorderComponent implements OnInit {
//order = new Order();
order :Order={orderId:0,
   amount: 0,
   billingDate: ""};

  constructor(private _route:Router ,private _service:OrderServiceService) { }

  ngOnInit(): void {
  }
  addorderformsubmit(){
   let orderPut={
    amount: this.order.amount,
    billingDate: this.order.billingDate,
    "customer":{
      "role":"customer",
      "password":"test123",
      "name":"Suraj",
      "email":"naiksaurjjjh@gmail.com",
      "contactNo":"9847887585",
      "dob":"2019-11-06"
  }
    }
   let resp=this._service.addOrderToRemote(orderPut)
   resp.subscribe(
    data=>{
      console.log("order added successfully");
      // this._route.navigate(['/order-list']);
    },
    error=>console.log(error)
  )
  console.log(this.order); 
  this.addOrder();
  }
  addOrder(){
    let order1=this._service.addOrderToRemote(this.order)
    let order2=order1.subscribe(
      data=>{
        console.log("order added successfully");
        // this._route.navigate(['/order-list']);
        this.gotolist();
      },
      error=>console.log(error)
    )
    //this._route.navigate(['/order-list']);
  }
  gotolist(){
  //  console.log("go back");
    // this._route.navigate(['order-list']);
  }

  

}
