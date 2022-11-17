// import { Component, OnInit } from '@angular/core';
// import { Router } from '@angular/router';
// import { PaymentServiceService } from 'src/app/payment/payment-service.service';
// @Component({
//   selector: 'app-payment-admin',
//   templateUrl: './payment-admin.component.html',
//   styleUrls: ['./payment-admin.component.css']
// })
// export class PaymentAdminComponent implements OnInit {

//   payment: any[] = [];
//   payment1: any;
//   payment3: any;
//   paymentId:any;
//   card:any;

//   constructor(private _service: PaymentServiceService, private router: Router) { }

//   ngOnInit() {
//     this._service.getAllPayments()
//     .subscribe((data =>{
//     this.payment = data}),
//     error => console.log("exception occured!"))
//   }

//   searchById(){
//     if(this.paymentId==""){
//       this.payment1 = "";
//     }
//       else
<<<<<<< HEAD
//       { 
=======
//       {
>>>>>>> 49694558855de3350446fa6ddf31b92a6b5dae48
//          this._service.paymentById(this.paymentId)
//          .subscribe((data1 => {
//           this.payment1 = data1}),
//           error => this.noResult());
<<<<<<< HEAD
              
//        }
//       }
//       public noResult(){
//            window.alert("Record not Found");
//       }
//       }
=======
>>>>>>> 49694558855de3350446fa6ddf31b92a6b5dae48

//        }
//       }
//       public noResult(){
//            window.alert("Record not Found");
//       }
//       }
