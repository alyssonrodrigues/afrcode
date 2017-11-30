import { Routes } from '@angular/router';

import { HobbiesComponent } from './hobbies/hobbies.component'
import { HelloWorldComponent  } from './helloWorld/helloWorld.component';

export const routes : Routes = [
  {
    path: '',
    component: HobbiesComponent
  },
  {
    path: 'helloworld',
    component: HelloWorldComponent
  }
];