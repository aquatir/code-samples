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

  getNoAuthData(): Observable<string> {
    return this.httpClient.get<string>(BACKEND_URLS.NO_AUTH_DATA)
  }

  getUserAuthDate() {
    return this.httpClient.get<string>(BACKEND_URLS.USER_AUTH_DATA)
  }

  getAdminAuthDate() {
    return this.httpClient.get<string>(BACKEND_URLS.ADMIN_AUTH_DATA)
  }

  getUserOrAdminAuthDate() {
    return this.httpClient.get<string>(BACKEND_URLS.USER_OR_ADMIN_AUTH_DATA)
  }

}
