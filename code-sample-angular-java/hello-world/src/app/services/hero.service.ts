import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { Hero } from '../datatypes/hero';
import { HEROES } from '../datatypes/mock-heroes';
import { MessageService } from './message.service';
import {HttpService} from "./http.service";

@Injectable()
export class HeroService {

  constructor(private httpService: HttpService, private messageService: MessageService) { }

  getHeroes(): Observable<any> {

    // TODO: send the message _after_ fetching the heroes
    this.messageService.add('HeroService: fetched heroes');
    return this.httpService.getHeroes();
  }

  getHero(id: number): Observable<Hero> {
    // TODO: send the message _after_ fetching the hero
    this.messageService.add(`HeroService: fetched hero id=${id}`);
    return of(HEROES.find(hero => hero.id === id));
  }
}
