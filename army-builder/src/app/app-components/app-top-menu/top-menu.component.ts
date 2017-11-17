import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html',
  styles: []
})
export class AppTopMenuComponent implements OnInit {

  constructor(
    private router: Router
  ) {}

  ngOnInit() {
  }

  navigate(path: string): void {
    this.router.navigate([path]);
  }

  logout() {
    console.log('AppTopMenuComponent: logout()');
  }

}
