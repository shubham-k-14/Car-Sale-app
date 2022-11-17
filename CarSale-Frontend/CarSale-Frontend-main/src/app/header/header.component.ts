import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { SignInComponent } from '../sign-in/sign-in.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  constructor(
    public service: SignInComponent,
    public userService: UserService
  ) {}

  ngOnInit(): void {}

  logout() {
    this.userService.logout();
  }
}
