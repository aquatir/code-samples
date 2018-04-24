import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";

import { AppComponent }         from './app.component';
import { AppRoutingModule }     from './app-routing.module';

import { DashboardComponent }   from './components/dashboard/dashboard.component';
import { HeroDetailsComponent }  from './components/hero-details/hero-details.component';
import { HeroesComponent }      from './components/heroes/heroes.component';
import { HeroService }          from './services/hero.service';
import { MessageService }       from './services/message.service';
import { MessageComponent }    from './components/message/message.component';
import { HttpService } from "./services/http.service";
import { TemplateSyntaxComponent } from './components/template-syntax/template-syntax.component';



@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    HeroesComponent,
    HeroDetailsComponent,
    MessageComponent,
    TemplateSyntaxComponent
  ],
  providers: [
    HttpService,
    HeroService,
    MessageService,
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
