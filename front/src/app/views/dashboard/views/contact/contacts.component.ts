import { Component } from '@angular/core';
import { AddContactComponent } from "./components/add-contact.component";
import { ContactListComponent } from "./components/contact-list.component";

@Component({
  selector: 'app-contacts',
  imports: [ AddContactComponent, ContactListComponent],
  template: `
  <div class="flex justify-center">
    <h2 class="text-xxl text-bold my-10">Contacts</h2>
  </div>
  <app-add-contact/>
  <app-contact-list/>`,
  styles: `
  :host{
    display:flex;
    flex-direction: column;
    
   
  }`
})
export class ContactsComponent {}
