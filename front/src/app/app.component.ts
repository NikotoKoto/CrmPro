import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./components/header.component";
import { FooterComponent } from "./components/footer.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, FooterComponent],
  template:`
  <app-header/>
  <main class=" flex-auto flex flex-col">
    <router-outlet/></main>
  <app-footer/>
  `,
  styles:`
  :host{
    display:flex;
    min-height: 100vh;
    flex-direction:column;
  }`

})
export class AppComponent {
  title = 'trainingForm';
}
