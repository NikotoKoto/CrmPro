import { AuthService } from './../shared/services/auth.service';
import { Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [RouterLink, RouterLinkActive],
  template: `
    <div class="text-bold"><a routerLink="/landingPage">CRM Pro</a></div>
    <ul class="flex gap-20 items-center">
      @if(isLoggedin()){
      <li>
        <span>Bienvenue {{ user().firstname }} </span>
      </li>
      <li><a (click)="logout()">Deconnexion</a></li>
      }@else if(isLoggedin() === false){

      <li>
        <a routerLink="/signIn" routerLinkActive="active-link">Se connecter</a>
      </li>
      } @else {
      <li>
        <a routerLink="/signIn" routerLinkActive="active-link">Se connecter</a>
      </li>
      }
    </ul>
  `,
  styles: `
  :host{
    width: 100%;
    min-width:350px;
    min-height: 85px;
    background-color: var(--section-bg);
    display:flex;
    align-items:center;
    justify-content: space-between;
    padding: var(--spacing-md);
     box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
     z-index: 10;

  }`,
})
export class HeaderComponent {
  private authService = inject(AuthService);
  isLoggedin = this.authService.isLoggedin;
  user = this.authService.currentUser;
  public logout() {
    this.authService.loggout();
  }
}
