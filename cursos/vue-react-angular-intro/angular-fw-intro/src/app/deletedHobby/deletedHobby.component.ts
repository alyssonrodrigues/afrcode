import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-deletedHobby',
  template: `<li (click)="onReaddHobby()" [ngStyle]="{color: 'red'}">{{ hobby }}</li>`
})
export class DeletedHobbyComponent {
  @Input() hobby : string;
  @Output() hobbyReadded = new EventEmitter<string>();

  onReaddHobby() {
    this.hobbyReadded.emit(this.hobby);
  }
}