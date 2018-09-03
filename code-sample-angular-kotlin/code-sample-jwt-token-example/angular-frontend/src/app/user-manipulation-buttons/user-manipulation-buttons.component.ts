import { Component, OnInit } from '@angular/core';
import {TokenService} from "../services/token.service";
import {HttpService} from "../services/http.service";

@Component({
  selector: 'app-user-manipulation-buttons',
  templateUrl: './user-manipulation-buttons.component.html',
  styleUrls: ['./user-manipulation-buttons.component.css']
})
export class UserManipulationButtonsComponent implements OnInit {

  username: string;
  password: string;

  noAuthDate = "";
  userAuthDate = "";
  adminAuthDate = "";
  userOrAdminAuthDate = "";

  constructor(private tokenService: TokenService, private httpService: HttpService) { }

  ngOnInit() {
  }

  login() {
    this.tokenService.authGetToken(this.username, this.password);
    this.username = "";
    this.password = "";
  }

  refreshTokens() {
    this.tokenService.authRefreshTokens();
  }

  getNoAuthData() {
    this.httpService.getNoAuthData().subscribe(
      successResponse => {this.noAuthDate = successResponse.data},
      failureResponse => {this.noAuthDate = "noAuthDate request failed"}
    )
  }

  getUserAuthDate() {
    this.httpService.getUserAuthDate().subscribe(
      successResponse => {this.userAuthDate = successResponse.data},
      failureResponse => {this.userAuthDate = "userAuthDate request failed"}
    )
  }

  getAdminAuthDate() {
    this.httpService.getAdminAuthDate().subscribe(
      successResponse => {this.adminAuthDate = successResponse.data},
      failureResponse => {this.adminAuthDate = "adminAuthDate request failed"}
    )
  }

  getUserOrAdminAuthDate() {
    this.httpService.getUserOrAdminAuthDate().subscribe(
      successResponse => {this.userOrAdminAuthDate = successResponse.data},
      failureResponse => {this.userOrAdminAuthDate = "userOrAdminAuthDate request failed"}
    )
  }

}
