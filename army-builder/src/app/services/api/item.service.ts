import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Item } from '../model/domain-model';

@Injectable()
export class ItemService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private url = 'api/items';

  constructor(private http: Http) { }

  getAll(): Promise<Item[]> {
    return this.http.get(this.url)
      .toPromise()
      .then(response => response.json().data as Item[])
      .catch(this.handleError);
  }

  getByArmyTemplate(templateId: string): Promise<Item[]> {
    return this.http.get(this.url + "?army=" + templateId)
      .toPromise()
      .then(response => response.json().data as Item[])
      .catch(this.handleError);
  }

  getItem(id: string): Promise<Item> {
    const url = `${this.url}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as Item)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
