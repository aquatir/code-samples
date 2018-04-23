import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {HttpConfig} from "../configs/http-config";

@Injectable()
export class HttpService {

  url =  "http://localhost:8000/heroes";

  constructor(private httpClient: HttpClient,
              private HTTP_CONFIG: HttpConfig) { }

  public getHeroes(): Observable<any> {
    console.log("Calling reroes by http: " + this.url);
    return this.httpClient.get(this.url);
  }

}
