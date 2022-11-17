import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../models/user.entity';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  //declaring base url
  url: string = 'http://localhost:8080/';

  //injecting dependencies
  constructor(private http: HttpClient, private router: Router) {}

  ///setting token in local storsge
  loginUser(token: string) {
    localStorage.setItem('token', token);
  }

  //checking if user logged in by token
  isLoggedIn() {
    let token = localStorage.getItem('token');
    if (token === undefined || token === '' || token === null) {
      return false;
    } else {
      return true;
    }
  }

  //clearing stored data in local storage when logged out
  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
    localStorage.removeItem('loggedIn');
    localStorage.removeItem('AdminLoggedIn');
    this.router.navigate(['/sign-in']);
    return true;
  }

  //getting token
  get token() {
    return localStorage.getItem('token');
  }

  //Sign in service that calls spring
  public signIn(user: any): Observable<User> {
    return this.http.post<User>(this.url + 'sign-in21', user);
  }

  //Sign out service that calls spring
  public signOut(user: any): Observable<User> {
    return this.http.post<User>(this.url + 'sign-out', user);
  }

  //for changing password
  public changePassword(user: any): Observable<User> {
    return this.http.put<User>(
      `${this.url}change-password/${user.userId}`,

      user
    );
  }
}
