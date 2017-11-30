import { Component } from '@angular/core';

@Component({
  selector: 'app-helloWorld',
  template: `
    <input type="text" [(ngModel)]="name">
    <p>Hello "{{ name }}"!</p>
  `
})
export class HelloWorldComponent {
  name = '';
}
