import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";

@Injectable()
export class TokenService {

  currentToken: string = "";

  constructor(private httpService: HttpService) { }

  getCurrentToken() {
    return this.currentToken;
  }

  refreshToken() {
    this.httpService.auth().subscribe(token =>
      this.currentToken = token);
  }

}
