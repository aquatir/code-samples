import { Component, OnInit } from '@angular/core';
import { Hero } from '../hero';
import { HeroService } from '../hero.service';
import {MessageService} from "../message.service";

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {

  constructor(private heroService: HeroService, private messageService: MessageService) { }

  heroes: Hero[];
  selectedHero: Hero;


  ngOnInit() {
    this.getHeroes();
  }


  getHeroes(): void {
    this.heroService.getHeroes()
      .subscribe(heroes => this.heroes = heroes);
  }


  onSelect(hero: Hero): void {
    this.messageService.add("Hero " + hero.name + " choosen");
    this.selectedHero = hero;
  }



}
