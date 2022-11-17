import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from '../order';
import { OrderServiceService } from '../order-service.service';

@Component({
  selector: 'app-view-order',
  templateUrl: './view-order.component.html',
  styleUrls: ['./view-order.component.css']
})
export class ViewOrderComponent implements OnInit {
  order = new Order(0,0,"");
  constructor(private _route:Router ,private _service:OrderServiceService,private _activateRoute : ActivatedRoute) { }

  ngOnInit(): void {
    let id = this._activateRoute.snapshot.paramMap.get('id');
    this._service.fetchOrderByIdFromRemote(1).subscribe(
      data => {
        console.log("data recieved!!");
        this.order =data;
      },
      error => console.log("exception occured!!")
    )
  }


  gotolist(){
    console.log("go back");
    this._route.navigate(['order-list']);
  }

}
