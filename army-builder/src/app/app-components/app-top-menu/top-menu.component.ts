import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html',
  styles: []
})
export class AppTopMenuComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
  }

  navigate(path: string): void {
    this.router.navigate([path]);
  }

  logout() {
    console.log('AppTopMenuComponent: logout()');
    this.authService.logout();
    this.router.navigateByUrl('/register');
  }

}
