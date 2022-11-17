import { Component, Input, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';

@Component({
  templateUrl: 'sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
})
export class SignUpComponent {
  flag: boolean = true;
  user: any;
  //  injecting dependencies
  constructor(private fb: FormBuilder) {}

  //creating sign up form
  public signUpForm = this.fb.group({
    role: ['', Validators.required],
    password: [
      '',
      [
        Validators.required,
        Validators.minLength(5),
        Validators.pattern('[0-9a-zA-Z@$]+'),
      ],
    ],
    confirmPassword: ['', Validators.required],
  });

  //for using variables easily
  get role() {
    return this.signUpForm.controls['role'];
  }
  get password() {
    return this.signUpForm.controls['password'];
  }
  get confirmPassword() {
    return this.signUpForm.controls['confirmPassword'];
  }

  //on submit method
  signUp() {
    this.user = {
      role: this.signUpForm.controls['role'].value,
      password: this.signUpForm.controls['password'].value,
    };
    console.log(this.user);
    this.toggle();
  }

  //toggle to get customer form
  toggle() {
    this.flag = false;
    return this.flag;
  }
}
