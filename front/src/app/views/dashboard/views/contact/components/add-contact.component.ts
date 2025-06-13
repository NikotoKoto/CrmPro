import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-contact',
  imports: [ReactiveFormsModule],
  template: `
    <div class="layoutForm">
      <h2 class="text-center py-20">Ajouter un client</h2>
      <form [formGroup]="clientForm" (ngSubmit)="submit()">
        <div class="flex flex-col">
          <div class="mb-20 flex flex-col">
            <label for="clientName">Nom du client</label>
          <input formControlName="clientName" type="text" id="clientName"/>
          </div>
          <div class="mb-20 flex flex-col">
            <label for="clientFirstName">Prenom du client</label>
          <input formControlName="clientFirstName" type="text" id="clientFirstName"/>
          </div>
          <div class="mb-20 flex flex-col">
            <label for="clientEmail">Email du client</label>
          <input formControlName="clientEmail" type="text" id="clientEmail"/>
          </div>
          <div class="mb-20 flex flex-col">
            <label for="clientPhone">Téléphone du client</label>
          <input formControlName="clientPhone" type="tel" id="clientPhone"/>
          </div>
          <div class="mb-20 flex flex-col">
            <label for="clientAdress">Adresse du client</label>
          <input formControlName="clientAdress" type="text" id="clientAdress"/>
          </div>
          <button class="btn btn-primary" type="submit" [disabled]="clientForm.invalid">Ajouter</button>
        </div>
      </form>
    </div>
  `,
  styles: `
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

  private fb = inject(FormBuilder);

  clientForm : FormGroup = this.fb.group({
    clientName:['',Validators.required],
    clientFirstname:['',Validators.required],
    clientEmail:['',Validators.required, Validators.email],
    clientPhone:[''],
    clientAdress:['']
  })



submit(){
  console.log(this.clientForm.getRawValue())
  }
}
