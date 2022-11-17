import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { AuthGuardService } from './auth-guard.service';

@Component({
  templateUrl: 'sign-in.component.html',
  styleUrls: ['sign-in.component.css'],
})
export class SignInComponent implements OnInit {
  user: any;
  id: any;
  message: any;
  loggedInStatus = JSON.parse(localStorage.getItem('loggedIn') || 'false');
  adminLoginStatus = JSON.parse(
    localStorage.getItem('AdminLoggedIn') || 'false'
  );

  ngOnInit() {}

  get isUserLoggedIn(): boolean {
    return JSON.parse(
      localStorage.getItem('loggedIn') || this.loggedInStatus.toString()
    );
  }
  get isAdminLoggedIn(): boolean {
    let flag = JSON.parse(
      localStorage.getItem('AdminLoggedIn') || this.adminLoginStatus.toString()
    );
    return flag;
  }
  setLoggedIn(value: boolean) {
    localStorage.setItem('loggedIn', 'true');
    this.loggedInStatus = true;
  }
  setAdminLogin(value: boolean) {
    localStorage.setItem('AdminLoggedIn', 'true');
    this.adminLoginStatus = true;
  }
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private userService: UserService
  ) {}
  public signInForm = this.fb.group({
    userId: ['', Validators.required],
    role: ['', Validators.required],
    password: [
      '',
      [
        Validators.required,
        Validators.minLength(5),
        Validators.pattern('[0-9a-zA-Z@$]+'),
      ],
    ],
  });
  get userId() {
    return this.signInForm.controls['userId'];
  }
  get role() {
    return this.signInForm.controls['role'];
  }
  get password() {
    return this.signInForm.controls['password'];
  }
  signIn() {
    this.user = {
      userId: this.signInForm.controls['userId'].value,
      role: this.signInForm.controls['role'].value,
      password: this.signInForm.controls['password'].value,
    };
    console.log(this.user);
    let response = this.userService.signIn(this.user);
    response.subscribe(
      (data: any) => {
        console.log('signed in');
        console.log(data);
        this.userService.loginUser(data.token);
        localStorage.setItem('userId', this.user.userId);
        if (data.role == 'Admin') {
          this.setAdminLogin(true);
          this.router.navigate(['admin/view']);
        } else {
          this.setLoggedIn(true);
          this.router.navigate(['customer-home']);
        }
      },
      (error) => {
        this.message = error.error.message;
      }
    );
  }
  cancel() {
    this.router.navigate(['']);
  }
}
