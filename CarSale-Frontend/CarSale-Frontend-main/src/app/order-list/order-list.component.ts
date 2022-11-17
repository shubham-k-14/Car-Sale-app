import { Component, OnInit } from '@angular/core';
import { OrderServiceService } from '../order-service.service';
import { Router } from '@angular/router';
import { Order } from '../order';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  private _route: any;
  order : Order[]=[];
  constructor(private _service:OrderServiceService, private router: Router) { }

  ngOnInit(): void {
        this._service.fetchOrderListFromRemote().subscribe(
               data=>console.log("response recieved"),
               error=>console.log("Exception Occured")
   )
  /* order={
     "orderId":1,
     "amount":12000,
     "billingDate":"2021-02-16"
   }*/
  }

  goToaddOrder(){
    this._route.navigate(['/order-list']);
  }

  goToUpdateOrder(id: number){
    console.log("id"+id);
    this._route.navigate(['/update-order',id]);
  }

  goToViewOrder(id: number){
    console.log("id"+id);
    this._route.navigate(['/view-order',id]);
  }

  deleteorder(id :number){
    this._service.deleteOrderByIdFromRemote(id).subscribe(
      data =>{
        console.debug("Order deleted successfully!");

      },
      error=> console.log("Exception occured!")
    )
  }
}
