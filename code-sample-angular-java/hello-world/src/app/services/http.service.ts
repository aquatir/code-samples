import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import {BACKEND_URLS} from "../configs/BACKEND_URLS";
import {Hero} from "../datatypes/hero";

@Injectable()
export class HttpService {

  constructor(private httpClient: HttpClient) { }

  getHeroes(): Observable<any> {
    return this.httpClient.get<Hero[]>(BACKEND_URLS.ALL_HEROES_URL);
  }

  getHero(id: number) : Observable<any> {
    return this.httpClient.get<Hero>(BACKEND_URLS.SINGLE_HERO_URL + "/" + id); /* TODO: refactor to interpolate? There must be other way! */
  }

}
