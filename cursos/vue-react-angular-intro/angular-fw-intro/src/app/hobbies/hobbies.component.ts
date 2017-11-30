import { Component } from '@angular/core';

@Component({
  selector: 'hobbies',
  templateUrl: './hobbies.component.html'
})
export class HobbiesComponent {
  hobbies = ['Running', 'Dancing', 'Singing'];
  deletedHobbies : string[] = [];
  newHobby = '';

  onAddNewHobby () {
    this.hobbies.push(this.newHobby)
    this.newHobby = ''
  }

  onHobbyDeleted (hobby) {
    var i = this.hobbies.indexOf(hobby)
    this.hobbies.splice(i, 1)
    this.deletedHobbies.push(hobby)
  }

  onHobbyReadded (deletedHobby) {
    this.hobbies.push(deletedHobby)
    var i = this.deletedHobbies.indexOf(deletedHobby)
    this.deletedHobbies.splice(i, 1)
  }
}