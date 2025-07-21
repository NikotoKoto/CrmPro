import { Component, inject } from '@angular/core';
import { AuthService } from '../../../../shared/services/auth.service';
import { ContactService } from '../contact/shared/service/contact.service';

@Component({
  selector: 'app-dashboard-home',
  imports: [],
  template: ` <h2 class="text-center">Bienvenue {{ user().firstname }} sur ton dashboard</h2>
  <div class="layout mt-20">
    
    <div class="card flex flex-col">
      <p class="flex justify-center">Nombre de clients:</p>
      <p class="flex justify-center">{{currentContact().length}}</p>
    </div>
    </div>
    `,
  styles: `
  :host{
    display:flex;
    flex-direction:column;
    
  }
  .layout{
    display:grid;
    align-items:center;
    padding:20px;
    gap:20px;
    grid-template-columns:repeat(3,1fr)
  }`,
})
export class DashboardHomeComponent {
  AuthService = inject(AuthService);
  contactService = inject(ContactService)
  currentContact = this.contactService.currentContact;
  user = this.AuthService.currentUser;
}
