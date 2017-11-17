import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {ArmyDomain} from '../../model/amy-domain.model';
import {ArmyDomainService} from '../../services/api/army-domain.service';
import {AuthService} from '../../services/auth/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './builder.component.html',
  styles: []
})
export class BuilderComponent implements OnInit {

  domains: ArmyDomain[];

  constructor(
    private authService: AuthService,
    private armyDomainService: ArmyDomainService,
    private router: Router
  ) {}

  ngOnInit() {
    this.armyDomainService.getAll().then(values => this.domains = values).catch(this.handleError);
  }

  gotoCreateArmy(): void {
    this.router.navigate(['/builder']);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
