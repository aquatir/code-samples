import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-inner-template-syntax',
  templateUrl: './inner-template-syntax.component.html',
  styleUrls: ['./inner-template-syntax.component.css']
})
export class InnerTemplateSyntaxComponent implements OnInit {

  @Input() size: number;
  @Output() sizeEvent = new EventEmitter<number>();

  constructor() { }

  ngOnInit() {
  }

  public increaseSize() {
    this.changeSize(1);
  }

  public descreaseSize() {
    this.changeSize(-1);
  }

  private changeSize(value) {
    this.size += value;
    this.sizeEvent.emit(this.size);
  }

}
