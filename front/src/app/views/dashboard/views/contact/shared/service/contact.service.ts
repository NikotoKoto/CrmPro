import { computed, Injectable, resource, signal } from '@angular/core';
import { Contact, ContactForm } from '../interface/contact.interface';



const API_CONTACT = 'http://localhost:8080/contacts';
@Injectable({
  providedIn: 'root'
})
export class ContactService {
    currentEditingContactId = signal<string | null>(null);

    setEditingContact(id: string | null) {
    this.currentEditingContactId.set(id);
  }
  getEditingContactId() {
    return this.currentEditingContactId();
  }

   currentContactResource = resource({
    loader: () => this.fetchCurrentContact(),
  });

  currentContact = computed(() => this.currentContactResource.value());

  async addContact(contact: ContactForm): Promise<Contact> {
    try {
      const response = await fetch(`${API_CONTACT}`, {
        method: 'POST',
        headers: {
          'Content-type': 'application/json',
        },
        body: JSON.stringify(contact),
        credentials: 'include',
      });
      if (response.ok) {
        const data = await response.json();
        this.currentContactResource.reload();
        return data;
      } else {
        throw new Error('Oops');
      }
    } catch {
      throw new Error('Oops');
    }
  }

  async fetchCurrentContact() {
    const response = await fetch(`${API_CONTACT}`, {
      credentials: 'include',
    });
    if (!response.ok) {
      return undefined;
    }
  
    return await response.json();
  }

  async editContact(contact: Contact){
    const {id, ...restContact} = contact;
    const response = await fetch(`${API_CONTACT}/${id}`,{
      method: 'PATCH',
      headers:{
        'Content-type': 'application/json'
      },
      body: JSON.stringify(restContact),
      credentials: 'include'
    })
    if(response.ok){
      this.currentContactResource.reload();
      return await response.json();
    }else{
      throw new Error("Oops")
    }
  }

  async deleteContact(contactId: string){
    await fetch(`${API_CONTACT}/${contactId}`,{
      method:'DELETE',
      credentials :'include'
    })
    this.currentContactResource.reload();

  }
  constructor() { }
}
