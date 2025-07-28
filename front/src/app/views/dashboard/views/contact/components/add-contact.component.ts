import { Component, effect, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ContactService } from '../shared/service/contact.service';
import { Contact, ContactForm } from '../shared/interface/contact.interface';

@Component({
  selector: 'app-add-contact',
  imports: [ReactiveFormsModule],
  template: `
    <div class="layoutForm">
      @if(this.contactService.currentEditingContactId()){
        <h2 class="text-center py-20">Modifier un client</h2>
      }@else {

        <h2 class="text-center py-20">Ajouter un client</h2>
      }
      <form [formGroup]="contactForm" (ngSubmit)="submit()">
        <div class="flex flex-col">
          <div class="mb-20 flex flex-col">
            <label for="name">Nom du client</label>
          <input formControlName="name" type="text" id="name"/>
          </div>
          <div class="mb-20 flex flex-col">
            <label for="email">Email du client</label>
          <input formControlName="email" type="text" id="email"/>
          </div>
          <div class="mb-20 flex flex-col">
            <label for="phone">Téléphone du client</label>
          <input formControlName="phone" type="tel" id="phone"/>
          </div>
          <div class="mb-20 flex flex-col">
            <label for="company">Entreprise</label>
          <input formControlName="company" type="text" id="company"/>
          </div>
          @if(this.contactId){
            <button class="btn btn-primary" type="submit" [disabled]="contactForm.invalid">Modifier</button>

          }@else{
            <button class="btn btn-primary" type="submit" [disabled]="contactForm.invalid">Ajouter</button>

          }
        </div>
      </form>
    </div>
  `,
  styles: `
  :host{
    padding:150px 0 150px 0;
  }
   .layoutForm {
    max-width: 500px; 
    margin: 0 auto;         
    background-color: white;
    border-radius: 8px;
    
  }

  

  h2 {
    text-align: center;
  }`
})
export class AddContactComponent {

contactService =inject(ContactService)
  private fb = inject(FormBuilder);
  contact = this.contactService.currentContact;
  contactId = this.contactService.currentEditingContactId();
  contactForm = this.fb.group({
    name:['',Validators.required],
    email:['',[Validators.required, Validators.email]],
    phone:[''],
    company:['',Validators.required]
  })

initContactFormEffect = effect(()=> {
  const contactId = this.contactService.currentEditingContactId();
  if(contactId){
    const contacts: Contact[] = this.contact();
    if(contacts){
      const {name, email, phone, company} = contacts.find(c => c.id == contactId)!;
      this.contactForm.patchValue({
        name,email,phone,company: company
      })
      console.log(this.contactForm)
    }else{
      this.initContactFormEffect.destroy();
    }
  }
})

async submit(){
  const contactId= this.contactService.currentEditingContactId();
  if(this.contactForm.invalid) return ;
  const contactForm : ContactForm = this.contactForm.getRawValue() as ContactForm;
  try{
    if(contactId){
      await this.contactService.editContact({
        ...this.contactForm.getRawValue(),
        id: contactId
      } as Contact)
      this.contactService.setEditingContact(null);
      this.contactForm.reset();
    }else{
      await this.contactService.addContact(contactForm);
      this.contactForm.reset();
    }

  }catch(error){
console.error("Error pendant l'ajout", error)
  }
  }
}
