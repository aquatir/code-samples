import { Component, OnInit } from '@angular/core';
import {TokenService} from "../services/token.service";

@Component({
  selector: 'app-user-manipulation-buttons',
  templateUrl: './user-manipulation-buttons.component.html',
  styleUrls: ['./user-manipulation-buttons.component.css']
})
export class UserManipulationButtonsComponent implements OnInit {

  username: string;
  password: string;

  constructor(private tokenService: TokenService) { }

  ngOnInit() {
  }

  login() {
    this.tokenService.authGetToken(this.username, this.password);
    this.username = "";
    this.password = "";
  }
}
