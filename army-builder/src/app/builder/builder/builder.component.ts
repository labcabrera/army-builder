import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './builder.component.html',
  styles: []
})
export class BuilderComponent implements OnInit {

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
  }

  gotoCreateArmy(): void {
    this.router.navigate(['/builder']);
  }

}
