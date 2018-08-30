import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { BACKEND_URLS } from "../configs/BACKEND_URLS";
import {Observable} from "rxjs/Observable";
import {TokenDto} from "../dto/tokenDto";

@Injectable()
export class HttpService {

  constructor(private httpClient: HttpClient) {

  }

  auth(username: string, password: string): Observable<TokenDto> {
    let request = {
      username: username,
      password: password
    };

    return this.httpClient.post<any>(BACKEND_URLS.AUTH, request)
  }
}
