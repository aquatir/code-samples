import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { HttpParams } from "@angular/common/http";
import { BACKEND_URLS } from "../configs/BACKEND_URLS";
import {Observable} from "rxjs/Observable";

@Injectable()
export class HttpService {

  constructor(private httpClient: HttpClient) {

  }

  auth(username: string, password: string): Observable<string> {
    let params = new HttpParams();
    params.set("username", username);
    params.set("password", password);

    return this.httpClient.post<string>(BACKEND_URLS.AUTH, {params: params});
  }
}
