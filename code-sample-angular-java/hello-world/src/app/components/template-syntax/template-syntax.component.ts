import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-template-syntax',
  templateUrl: './template-syntax.component.html',
  styleUrls: ['./template-syntax.component.css']
})
export class TemplateSyntaxComponent implements OnInit {

  displayTextAfterHeaderClicked: boolean;
  heroImage: string;

  constructor() {
    this.displayTextAfterHeaderClicked = false;
    this.heroImage = "/assets/images/hero.png";
  }

  ngOnInit() {
  }

  public headerClicked() {
    this.displayTextAfterHeaderClicked = true;
  }
}
