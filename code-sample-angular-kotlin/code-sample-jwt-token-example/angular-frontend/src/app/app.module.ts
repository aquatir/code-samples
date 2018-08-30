import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { CurrentUserInfoComponent } from './current-user-info/current-user-info.component';
import { UserManupulationButtonsComponent } from './user-manupulation-buttons/user-manupulation-buttons.component';
import { TokenService } from "./services/token.service";
import {HttpClientModule} from "@angular/common/http";
import {HttpService} from "./services/http.service";
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AppComponent,
    CurrentUserInfoComponent,
    UserManupulationButtonsComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [TokenService, HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
