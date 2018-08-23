import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { BACKEND_URLS } from "../configs/BACKEND_URLS";
import {Observable} from "rxjs/Observable";

@Injectable()
export class HttpService {

  constructor(private httpClient: HttpClient) {

  }

  auth(): Observable<string> {
    return this.httpClient.get<string>(BACKEND_URLS.AUTH);
  }
}
