import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";

@Injectable()
export class TokenService {

  private accessToken: string = "";
  private refreshToken: string = "";

  constructor(private httpService: HttpService) { }

  getCurrentToken() {
    return this.accessToken;
  }

  authGetToken(username: string, password: string) {
    this.httpService.auth(username, password).subscribe(
      successTokenDto => {
        this.accessToken = successTokenDto.accessToken;
        this.refreshToken = successTokenDto.refreshToken;
      },
        failure => this.accessToken = ""
      );
  }

}
