import {Injectable} from '@angular/core';
import {Headers, Http} from '@angular/http';

import {ArmyDomain} from '../../model/amy-domain.model';
import {AuthService} from '../auth/auth.service';

@Injectable()
export class ArmyDomainService {

  private headers = new Headers({
    'Content-Type': 'application/json'
  });

  private url = 'http://localhost:8080/api/domains';

  constructor(
    private authService: AuthService,
    private http: Http
  ) {}

  getAll(): Promise<ArmyDomain[]> {
    return this.http.get(this.url)
      .toPromise()
      .then(response => response.json().data as ArmyDomain[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
