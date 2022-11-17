import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { PaymentServiceService } from './payment-service.service';
import { Payment } from '../Payment';
import { Card } from 'src/app/Card';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent {

  pay: any;
  show: Boolean = false;
  showEye: Boolean = false;
  isSubmitted = true;
  card: Card = {
    cardName: '',
    cardNumber: '',
    cardExpiry: '',
    cvv: ''
  };
  payment: Payment = {
    type: 'CC',
    status: 'success',
    card: this.card
  };

  constructor(private _service: PaymentServiceService, private router: Router, private fb: FormBuilder) { }


  onSubmit = this.fb.group({
    cardName: ['', [Validators.required, Validators.pattern('^[A-Za-z ]{2,}')]],
    cardNumber: ['', [Validators.required, Validators.pattern('(^4[0-9]{15})|(^50[0-9]{14})|(^34[0-9]{14})|(^37[0-9]{14})|(^6[0-9]{15})')]],
    cvv: ['', [Validators.required, Validators.pattern('[0-9]{3}')]],
    expiryDate: ['', [Validators.required, Validators.pattern('(?:0[1-9]|1[0-2])/(?:2[2-9]|3[0-5])')]]
  })

  get cardNumber() {
    return this.onSubmit.controls['cardNumber'];
  }
  get cardName() {
    return this.onSubmit.controls['cardName'];
  }
  get expiryDate() {
    return this.onSubmit.controls['expiryDate'];
  }
  get cvv() {
    return this.onSubmit.controls['cvv'];
  }
  public paymentAdd() {
    this._service.makePayment(this.payment).subscribe((data) => {
      console.log(data);
      this.goToSuccess();
    });
  }

  goToSuccess() {
    this.router.navigate(['/success']);
  }

  showPassword() {
    this.show = !this.show;
    this.showEye = !this.showEye;
  }

  public cancel() {
    window.alert("order cancelled");
  }

}
