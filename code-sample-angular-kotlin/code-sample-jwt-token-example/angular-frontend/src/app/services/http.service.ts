import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { BACKEND_URLS } from "../configs/BACKEND_URLS";
import {Observable} from "rxjs/Observable";
import {AccessAndRefreshTokenResponse} from "../dto/responses/accessAndRefreshTokenResponse";
import {StringReqResp} from "../dto/both/stringReqResp";
import {AccessTokenByRefreshTokenRequest} from "../dto/requests/accessTokenByRefreshTokenRequest";
import {AccessTokenResponse} from "../dto/responses/accessTokenResponse";

@Injectable()
export class HttpService {

  constructor(private httpClient: HttpClient) {

  }

  auth(username: string, password: string): Observable<AccessAndRefreshTokenResponse> {
    let request = {
      username: username,
      password: password
    };

    return this.httpClient.post<any>(BACKEND_URLS.AUTH, request)
  }

  refresh(token: string) : Observable<AccessTokenResponse> {
    let request = new AccessTokenByRefreshTokenRequest(token);

    return this.httpClient.post<AccessTokenResponse>(BACKEND_URLS.AUTH_REFRESH, request)
  }


  /**
   *  Auth header is added to request by AuthInterceptor (Interceptor)
   */
  getNoAuthData(): Observable<StringReqResp> {
    return this.httpClient.get<StringReqResp>(BACKEND_URLS.NO_AUTH_DATA)
  }

  getUserAuthDate(): Observable<StringReqResp> {
    return this.httpClient.get<StringReqResp>(BACKEND_URLS.USER_AUTH_DATA)
  }

  getAdminAuthDate(): Observable<StringReqResp> {
    return this.httpClient.get<StringReqResp>(BACKEND_URLS.ADMIN_AUTH_DATA)
  }

  getUserOrAdminAuthDate(): Observable<StringReqResp> {
    return this.httpClient.get<StringReqResp>(BACKEND_URLS.USER_OR_ADMIN_AUTH_DATA)
  }
}
