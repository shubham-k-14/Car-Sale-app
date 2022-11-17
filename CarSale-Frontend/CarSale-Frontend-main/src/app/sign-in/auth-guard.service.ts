import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { SignInComponent } from './sign-in.component';
import { User } from '../models/user.entity';

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(
    private router: Router,
    private service: SignInComponent,
    private userService: UserService
  ) {}
  //return true or false to get access to user
  //overridded method
  canActivate(route: ActivatedRouteSnapshot): boolean {
    //checks from user service
    if (!this.userService.isLoggedIn()) {
      this.router.navigate(['/sign-in']);
    }
    return this.userService.isLoggedIn();
  }
}
