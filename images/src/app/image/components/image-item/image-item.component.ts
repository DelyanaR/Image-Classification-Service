import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Image} from "../../../image";

@Component({
  selector: 'app-image-item',
  templateUrl: './image-item.component.html',
  styleUrls: ['./image-item.component.scss']
})
export class ImageItemComponent {
  @Input() image?: Image;
  @Output() popup: EventEmitter<boolean>=new EventEmitter<boolean>();

  load: boolean=false;
  constructor() { }

  public exitPopUp(){
    this.popup.emit(false);
  }

}
