import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-addedhobby',
  template: `<li (click)="onDeleteHobby()">{{ hobby }}</li>`
})
export class AddedHobbyComponent {
  @Input() hobby : string;
  @Output() hobbyDeleted = new EventEmitter<string>();

  onDeleteHobby() {
    this.hobbyDeleted.emit(this.hobby);
  }
}
