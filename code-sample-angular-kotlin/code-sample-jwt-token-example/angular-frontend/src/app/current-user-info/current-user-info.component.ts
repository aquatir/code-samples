import { Component, OnInit } from '@angular/core';
import {TokenService} from "../services/token.service";

@Component({
  selector: 'app-current-user-info',
  templateUrl: './current-user-info.component.html',
  styleUrls: ['./current-user-info.component.css']
})
export class CurrentUserInfoComponent implements OnInit {

  token: string;

  constructor(private tokenService: TokenService) {

  }

  ngOnInit() {
    this.token = this.tokenService.currentToken;
  }

}
