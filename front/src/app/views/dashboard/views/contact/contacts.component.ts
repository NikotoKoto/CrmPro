import { Component } from '@angular/core';
import { AddContactComponent } from "./components/add-contact.component";
import { ContactListComponent } from "./components/contact-list.component";

@Component({
  selector: 'app-contacts',
  imports: [ AddContactComponent, ContactListComponent],
  template: `
  <app-add-contact/>
  <app-contact-list/>`,
  styles: `
  :host{
    display:grid;
    grid-template-columns: repeat(2,1fr);
    
  }`
})
export class ContactsComponent {}
