import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-inner-template-syntax',
  templateUrl: './inner-template-syntax.component.html',
  styleUrls: ['./inner-template-syntax.component.css']
})
export class InnerTemplateSyntaxComponent implements OnInit {

  @Input() size: number;

  constructor() { }

  ngOnInit() {
  }

  public increaseSize() {
    this.size += 1;
  }

  public descreaseSize() {
    this.size -=1;
  }

}
