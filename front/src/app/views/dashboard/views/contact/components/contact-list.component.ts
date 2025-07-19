import { Component, inject } from '@angular/core';
import { DashboardService } from '../../../shared/services/dashboard.service';
import { ContactService } from '../shared/service/contact.service';
import { LucideAngularModule,Delete,SquarePen } from "lucide-angular";
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-contact-list',
  imports: [LucideAngularModule,RouterLink],
  template: `

<div class="contacts-container">
  <h2>Mes contacts</h2>
<table>
  <thead>
    <tr>
      <th>Nom</th>
      <th>Email</th>
      <th>Téléphone</th>
      <th>Entreprise</th>
      <th>Actions</th>
    </tr>
  </thead>
  <tbody>
     @for(contact of currentContacts(); track contact.id){
    <tr>
      <td>{{ contact.name }}</td>
      <td>{{ contact.email }}</td>
      <td>{{ contact.phone }}</td>
      <td>{{ contact.company }}</td>
      <td>
        <button (click)="editContact(contact.id)"><lucide-icon [img]="SquarePen" class="icon blue"/></button>
        <button (click)="deleteContact(contact.id)"><lucide-icon [img]="Delete" class="icon red"/></button>
      </td>
    </tr>
     }
  </tbody>
</table>
</div>
    
  `,
  styles: `
  :host {
  display: flex;
  justify-content: center;

  .contacts-container {
    width: 100%;
    max-width: 800px;
  }

  table {
  margin-top:50px;
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border-bottom: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #f7f7f7;
}

button {
  margin-right: 4px;
  border-radius: 15px;
  margin-left: 4px;
  border:none;
  cursor: pointer;
  background:none;
  }
  
  .blue{
    color:var(--primary);
  }
  .red{
    color:var(--danger);
  }
}`
})
export class ContactListComponent {
  readonly Delete = Delete;
  readonly SquarePen = SquarePen;
private contactService = inject(ContactService);

currentContacts = this.contactService.currentContact;

editContact(contactId : string){
this.contactService.setEditingContact(contactId);
// console.log("id", contactId)
}
deleteContact(contactId : string){
this.contactService.deleteContact(contactId);
}
}
