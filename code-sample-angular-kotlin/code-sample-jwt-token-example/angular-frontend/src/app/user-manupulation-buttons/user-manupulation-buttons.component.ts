import { Component, OnInit } from '@angular/core';
import {TokenService} from "../services/token.service";

@Component({
  selector: 'app-user-manupulation-buttons',
  templateUrl: './user-manupulation-buttons.component.html',
  styleUrls: ['./user-manupulation-buttons.component.css']
})
export class UserManupulationButtonsComponent implements OnInit {

  username: string;
  password: string;

  constructor(private tokenService: TokenService) { }

  ngOnInit() {
  }

  login() {
    this.tokenService.refreshToken(this.username, this.password);
    this.username = "";
    this.password = "";
  }
}
