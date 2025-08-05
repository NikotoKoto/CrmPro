import { Component, input, output } from '@angular/core';
import { LucideAngularModule,Delete,SquarePen } from "lucide-angular";
import { RouterLink } from '@angular/router';
import { Contact } from '../shared/interface/contact.interface';

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
     @for(contact of contacts(); track contact.id){
    <tr>
      <td>{{ contact.name }}</td>
      <td>{{ contact.email }}</td>
      <td>{{ contact.phone }}</td>
      <td>{{ contact.company.name }}</td>
      <td>
        <button (click)="isEdit.emit(contact.id)"><lucide-icon [img]="SquarePen" class="icon blue"/></button>
        <button (click)="isDelete.emit(contact.id)"><lucide-icon [img]="Delete" class="icon red"/></button>
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
contacts = input<Contact[]>();
isEdit = output<string>();
isDelete = output<string>();
}
