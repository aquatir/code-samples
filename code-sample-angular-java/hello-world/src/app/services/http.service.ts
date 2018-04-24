import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";

@Injectable()
export class HttpService {

  constructor(private httpClient: HttpClient) { }

  /* TODO: Refactor into const class/file (What's the correct way with angular?) */
  API_BASE: string =  "http://localhost:8000";
  ALL_HEROES_URL: string = this.API_BASE + "/heroes";
  SINGLE_HERO_URL: string = this.API_BASE + "/hero";

  getHeroes(): Observable<any> {
    return this.httpClient.get(this.ALL_HEROES_URL);
  }

  getHero(id: number) : Observable<any> {
    return this.httpClient.get(this.SINGLE_HERO_URL + "/" + id); /* TODO: refactor to interpolate? There must be other way! */
  }

}
