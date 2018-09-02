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

  getNoAuthData() {
    this.httpService.getNoAuthData().subscribe(
      successData => {this.noAuthDate = successData},
      failure => {this.noAuthDate = "noAuthDate request failed"}
    )
  }

  getUserAuthDate() {
    this.httpService.getUserAuthDate().subscribe(
      successData => {this.userAuthDate = successData},
      failure => {this.userAuthDate = "userAuthDate request failed"}
    )
  }

  getAdminAuthDate() {
    this.httpService.getAdminAuthDate().subscribe(
      successData => {this.adminAuthDate = successData},
      failure => {this.adminAuthDate = "adminAuthDate request failed"}
    )
  }

  getUserOrAdminAuthDate() {
    this.httpService.getUserOrAdminAuthDate().subscribe(
      successData => {this.userOrAdminAuthDate = successData},
      failure => {this.userOrAdminAuthDate = "userOrAdminAuthDate request failed"}
    )
  }

}
