import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AddedHobbyComponent } from './addedHobby/addedHobby.component';
import { DeletedHobbyComponent } from './deletedHobby/deletedHobby.component';
import { HobbiesComponent } from './hobbies/hobbies.component';
import { HelloWorldComponent } from './helloWorld/helloWorld.component';
import { MenuComponent } from './menu/menu.component';

import { routes } from './routes';

@NgModule({
  declarations: [
    AppComponent,
    AddedHobbyComponent,
    DeletedHobbyComponent,
    HobbiesComponent,
    HelloWorldComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
