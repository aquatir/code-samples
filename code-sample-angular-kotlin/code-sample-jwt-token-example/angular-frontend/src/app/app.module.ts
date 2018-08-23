import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { CurrentUserInfoComponent } from './current-user-info/current-user-info.component';
import { UserManupulationButtonsComponent } from './user-manupulation-buttons/user-manupulation-buttons.component';


@NgModule({
  declarations: [
    AppComponent,
    CurrentUserInfoComponent,
    UserManupulationButtonsComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
