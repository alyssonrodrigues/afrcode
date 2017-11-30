import { Component } from '@angular/core';

@Component({
  selector: 'app-helloworld',
  template: `
    <input type="text" [(ngModel)]="name">
    <p>Hello "{{ name }}"!</p>
  `
})
export class HelloWorldComponent {
  name = '';
}
