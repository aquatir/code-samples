import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { FormsModule } from "@angular/forms";

import { AppComponent } from './app.component';
import { CurrentUserInfoComponent } from './current-user-info/current-user-info.component';
import { UserManipulationButtonsComponent } from "./user-manipulation-buttons/user-manipulation-buttons.component";

import { TokenService } from "./services/token.service";
import { HttpService } from "./services/http.service";
import { AuthInterceptor } from "./interceptors/auth-interceptor.service";



@NgModule({
  declarations: [
    AppComponent,
    CurrentUserInfoComponent,
    UserManipulationButtonsComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [TokenService, HttpService,
    {provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
