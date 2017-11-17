import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {AuthService} from '../../services/auth/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: []
})
export class HomeComponent implements OnInit {

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit() {
    console.log('HomeComponent: ngOnInit()');
    const userId = this.authService.getUserId();
    if (!userId) {
      this.router.navigate(['/register']);
    }
    console.log('HomeComponent: userId: ' + userId);
  }

  createRoster() {
    this.router.navigate(['/builder']);
  }

}
