
// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';
// @Injectable({
//   providedIn: 'root'
// })
// export class PaymentServiceService {

//   constructor( private http:HttpClient) { }
  
//    public makePayment(payment:Payment): Observable<any>{
//     return this.http.post('http://localhost:8080/payment/addpayment',payment);
//   }

//   public getAllPayments():Observable<any>{
//     return this.http.get('http://localhost:8080/payment/getallpayment');
//   }

//   public paymentById(paymentId:number):Observable<any>{
//      return this.http.get('http://localhost:8080/payment/getpayment/'+paymentId);
//   }
// }

// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// //import { Payment } from '../Payment';
// import { Observable } from 'rxjs';
// @Injectable({
//   providedIn: 'root',
// })
// export class PaymentServiceService {
//   constructor(private http: HttpClient) {}

//   //  public makePayment(payment:Payment): Observable<any>{
//   //   return this.http.post('http://localhost:8080/payment/addpayment',payment);
//   // }

//   public getAllPayments(): Observable<any> {
//     return this.http.get('http://localhost:8080/payment/getallpayment');
//   }

//   public paymentById(paymentId: number): Observable<any> {
//     return this.http.get(
//       'http://localhost:8080/payment/getpayment/' + paymentId
//     );
//   }
// }

