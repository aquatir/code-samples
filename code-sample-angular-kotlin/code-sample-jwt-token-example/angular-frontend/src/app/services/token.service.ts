import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";

/**
 * We create a dedicated token service to serve and retrieve tokens. We use this service as storage for tokens.
 * You may want to store tokens in LocalStorage / Cookies in order to allow user to authenticate transparently
 * during some period of time.
 *
 * Here we are storing tokens in service for simplicity
 */
@Injectable()
export class TokenService {

  private accessToken: string = "";
  private refreshToken: string = "";

  constructor(private httpService: HttpService) { }

  getCurrentAccessToken() {
    return this.accessToken;
  }

  getCurrentRefreshToken() {
    return this.refreshToken;
  }

  authGetToken(username: string, password: string) {
    this.httpService.auth(username, password).subscribe(
      successTokenDto => {
        this.accessToken = successTokenDto.accessToken;
        this.refreshToken = successTokenDto.refreshToken;
      },
        failure => {
          this.accessToken = "";
          this.refreshToken = "";
        }
      )
  }

}
