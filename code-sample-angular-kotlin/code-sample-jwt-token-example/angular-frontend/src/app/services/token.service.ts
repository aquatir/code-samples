import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";

@Injectable()
export class TokenService {

  private currentToken: string = "";

  constructor(private httpService: HttpService) { }

  getCurrentToken() {
    return this.currentToken;
  }

  refreshToken(username: string, password: string) {
    this.httpService.auth(username, password).subscribe(
      successTokenDto => this.currentToken = successTokenDto.token,
        failure => this.currentToken = "");
  }

}
