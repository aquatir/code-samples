import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-template-syntax',
  templateUrl: './template-syntax.component.html',
  styleUrls: ['./template-syntax.component.css']
})
export class TemplateSyntaxComponent implements OnInit {

  displayTextAfterHeaderClicked: boolean;
  colorIsRed: boolean;
  heroImage: string;
  outerSize: number;

  constructor() {
    this.displayTextAfterHeaderClicked = false;
    this.colorIsRed = false;
    this.heroImage = "/assets/images/hero.png";
    this.outerSize = 4;
  }

  ngOnInit() {
  }

  public headerClicked() {
    this.displayTextAfterHeaderClicked = true;
  }

  public changeColorButtonClicked() {
    this.colorIsRed = true;
  }
}
