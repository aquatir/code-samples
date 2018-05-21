import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';

import { Hero } from '../datatypes/hero';
import { MessageService } from './message.service';
import { HttpService } from "./http.service";

@Injectable()
export class HeroService {

  constructor(private httpService: HttpService, private messageService: MessageService) { }

  getHeroes(): Observable<Hero[]> {

    this.messageService.add('HeroService: fetched heroes');
    return this.httpService.getHeroes();
  }

  getHero(id: number): Observable<Hero> {
    this.messageService.add(`HeroService: fetched hero id=${id}`);
    return this.httpService.getHero(id);
  }
}
