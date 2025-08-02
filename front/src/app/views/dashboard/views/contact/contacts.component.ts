import { Component, inject } from '@angular/core';
import { AddContactComponent } from "./components/add-contact.component";
import { ContactListComponent } from "./components/contact-list.component";
import { ContactService } from './shared/service/contact.service';

@Component({
  selector: 'app-contacts',
  imports: [ AddContactComponent, ContactListComponent],
  template: `
  <div class="flex justify-center">
    <h2 class="text-xxl text-bold my-10">Contacts</h2>
  </div>
  <app-add-contact [contactId]="currentContactEditing()"/>
  <app-contact-list [contacts]="currentContacts()" (isEdit)="editContact($event)" (isDelete)="deleteContact($event)"/>`,
  styles: `
  :host{
    display:flex;
    flex-direction: column;
    
   
  }`
})
export class ContactsComponent {
private contactService = inject(ContactService);
currentContacts = this.contactService.currentContact;
currentContactEditing = this.contactService.currentEditingContactId
editContact(contactId : string){
this.contactService.setEditingContact(contactId);
}

  deleteContact(contactId : string){
this.contactService.deleteContact(contactId);
}
constructor(){
  console.log(this.currentContacts)
}
}
