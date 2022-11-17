import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { SignInComponent } from './sign-in.component';

@Injectable()
//for admin access only
//checking the child side of route
export class AdminGuardService implements CanActivateChild {
  constructor(private router: Router, private service: SignInComponent) {}
  //return true or false depending on admin logged in status
  canActivateChild(): boolean {
    if (!this.service.isAdminLoggedIn) {
      this.router.navigate(['/sign-in']);
    }
    return this.service.isAdminLoggedIn;
  }
}
