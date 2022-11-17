import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Order } from './order';

@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  constructor(private _http:HttpClient) { }

  fetchOrderListFromRemote():Observable<any>{
  return  this._http.get<Order[]>("http://localhost:8080/order/getallorders");
  }
  addOrderToRemote(order:any):Observable<any>{
    return  this._http.post("http://localhost:8080/order/addorder",order);
    }
  updateOrderToRemote(order : Order):Observable<any>{
      return  this._http.put<any>("http://localhost:8080/order/updateorder",order);
   }
  fetchOrderByIdFromRemote(id : number):Observable<any>{
    return  this._http.get<any>("http://localhost:8080/order/getorderdetailsbyId"+id);
 }
 deleteOrderByIdFromRemote(id : number):Observable<any>{
  return  this._http.delete<any>("http://localhost:8080/order/delete"+id);
}

}
