import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormControl,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.entity';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
})
export class ChangePasswordComponent implements OnInit {
  id!: number;
  user!: User;
  message!: string;

  //On initialization method
  ngOnInit(): void {
    this.id = <number>(<any>localStorage.getItem('userId'));
  }

  //injecting dependency

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private userService: UserService
  ) {}

  //change password form using form bulding
  public changePasswordForm = this.fb.group({
    userId: ['', Validators.required],
    role: ['', Validators.required],
    password: [''],
    newPassword: [
      '',
      [
        Validators.required,
        Validators.minLength(5),
        Validators.pattern('[0-9a-zA-Z@$]+'),
      ],
    ],
    confirmPassword: ['helo', Validators.required],
  });

  //for using variables easily
  get userId() {
    return this.changePasswordForm.controls['userId'];
  }
  get role() {
    return this.changePasswordForm.controls['role'];
  }
  get password() {
    return this.changePasswordForm.controls['password'];
  }
  get newPassword() {
    return this.changePasswordForm.controls['newPassword'];
  }
  get confirmPassword() {
    return this.changePasswordForm.controls['confirmPassword'];
  }

  //on submit method
  changePasswordSubmit() {
    //creating json object
    this.user = {
      userId: this.userId.value,
      role: this.role.value,
      password: this.newPassword.value,
    };
    console.log(this.user);
    //subscribing to observable
    let resp = this.userService.changePassword(this.user);
    resp.subscribe(
      (data) => {
        //printing error message
        alert('password changed');
        this.router.navigate(['/customer-profile']);
      },
      (error) => {
        //taking error message
        console.log(error);
        alert('password changed');
        this.router.navigate(['/customer-profile']);
      }
    );
  }

  //for cancel button
  cancel() {
    this.router.navigate(['']);
  }
}
