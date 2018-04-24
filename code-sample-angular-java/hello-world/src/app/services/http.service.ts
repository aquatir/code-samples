import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class HttpService {

  url : string = "http://localhost:8000/heroes";

  constructor(private httpClient: HttpClient) { }

  getHeroes(): Observable<any> {
    return this.httpClient.get(this.url);
  }

}
