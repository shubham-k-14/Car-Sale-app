import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

@Injectable()
export class AuthIntercepter implements HttpInterceptor {
  constructor(private userService: UserService) {}
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let newReq = req;
    let token = this.userService.token;

    if (token != null) {
      newReq = newReq.clone({
        setHeaders: { Authorization: 'Bearer ' + token },
      });
    }
    console.log(newReq.headers.get('Authorization'));
    return next.handle(newReq);
  }
}
