import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { BACKEND_URLS } from "../configs/BACKEND_URLS";
import {Observable} from "rxjs/Observable";

@Injectable()
export class HttpService {

  constructor(private httpClient: HttpClient) {

  }

  auth(username: string, password: string): Observable<string> {
    console.log("Requiest params: " + username + " password: " + password);
    let request = {
      username: username,
      password: password
    };

    console.log("Request body is going to be: " + JSON.stringify(request));

    return this.httpClient.post<string>(BACKEND_URLS.AUTH, request);
  }
}
