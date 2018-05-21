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
  size: number;

  constructor() {
    this.displayTextAfterHeaderClicked = false;
    this.colorIsRed = false;
    this.heroImage = "/assets/images/hero.png";
    this.size = 4;
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
