import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../models/user.entity';
import { UserService } from '../service/user.service';
import { AuthGuardService } from './auth-guard.service';

@Component({
  templateUrl: 'sign-in.component.html',
  styleUrls: ['sign-in.component.css'],
})
export class SignInComponent implements OnInit {
  user!: User;
  id: any;
  message: any;
  //for storing user login status
  //used local storage be logged in even after application is restarted
  loggedInStatus = JSON.parse(localStorage.getItem('loggedIn') || 'false');

  //for storing if admin is logged in or not
  adminLoginStatus = JSON.parse(
    localStorage.getItem('AdminLoggedIn') || 'false'
  );

  //On initialization method
  ngOnInit() {}

  //injecting dependencies
  constructor(
    private fb: FormBuilder, //form builder
    private router: Router, //router
    private userService: UserService //injecting user service
  ) {}

  //for checking if user is logged in
  get isUserLoggedIn(): boolean {
    return JSON.parse(
      localStorage.getItem('loggedIn') || this.loggedInStatus.toString()
    );
  }

  //for checking if admin is logged in or not
  get isAdminLoggedIn(): boolean {
    let flag = JSON.parse(
      localStorage.getItem('AdminLoggedIn') || this.adminLoginStatus.toString()
    );
    return flag;
  }

  //setting user logged in status
  setLoggedIn(value: boolean) {
    localStorage.setItem('loggedIn', 'true');
    this.loggedInStatus = true;
  }

  //setting admin logged in status
  setAdminLogin(value: boolean) {
    localStorage.setItem('AdminLoggedIn', 'true');
    this.adminLoginStatus = true;
  }

  //initialising sign in form
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

  //for using variables easily
  get userId() {
    return this.signInForm.controls['userId'];
  }
  get role() {
    return this.signInForm.controls['role'];
  }
  get password() {
    return this.signInForm.controls['password'];
  }

  //on submit method
  signIn() {
    //creating json object
    this.user = {
      userId: this.signInForm.controls['userId'].value,
      role: this.signInForm.controls['role'].value,
      password: this.signInForm.controls['password'].value,
    };
    let response = this.userService.signIn(this.user);
    //subscribing to onbservable
    response.subscribe(
      (data: any) => {
        //printing message
        console.log('signed in');
        this.userService.loginUser(data.token);
        //setting user id in  login
        localStorage.setItem('userId', <string>(<any>this.user.userId));
        //checking role
        if (data.role == 'Admin') {
          this.setAdminLogin(true);
          this.router.navigate(['admin/admin-home']);
        } else {
          this.setLoggedIn(true);
          this.router.navigate(['customer-home']);
        }
      },
      //checking if error in response
      (error) => {
        //printing error message
        this.message = error.error.message;
      }
    );
  }
  //cancel buttton
  cancel() {
    this.router.navigate(['']);
  }
}
