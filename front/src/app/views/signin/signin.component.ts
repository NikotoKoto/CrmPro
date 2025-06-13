import { AuthService } from './../../shared/services/auth.service';
import { loginForm } from './../../shared/interfaces/user.interfaces';

import { Component, inject, signal } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  imports: [ReactiveFormsModule],
  template: `
  <h2 class="mb-20 text-xxl">CRM PRO</h2>
    <form [formGroup]="loginForm" (ngSubmit)="submit()">
      <div class="flex flex-col mb-20">
        <label for="email">Email</label>
        <input type="text" formControlName="email" id="email"/>
       @if(emailControl.errors?.['required'] && emailControl.touched){
          <p class="error">Email obligatoire</p>
          } @else if(emailControl.errors?.['email'] && emailControl.touched){
          <p class="error">Email non valide</p>
          }
      </div>
      <div class="flex flex-col mb-20">
        <label for="password">Mot de passe</label>
        <input type="password" formControlName="password" id="password"/>
         @if(passwordControl.errors?.['required'] && passwordControl.touched){
            <p class="error"> mot de passe obligatoire</p>
         }@else if (passwordControl.errors?.['minlength'] &&
          passwordControl.touched) {
          <p class="error">taille du mot de passe trop court</p>
          }

      </div>
      <div class=" flex justify-center items-center">
        <button class="btn btn-primary">Me connecter</button>
      </div>
      <a class="mt-20" >Mot de passe oublié?</a>
      </form>
  `,
  styles: `
  :host{
    flex:1;
    background-color : var(--section-bg);
    display:flex;
    flex-direction: column;
    justify-content:center;
    align-items:center;
  }
  form {
      padding: 24px;
      border-radius: 8px;
      background-color: #fff;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 400px;
    }`
})
export class SigninComponent {
private fb = inject(FormBuilder)
private router = inject(Router)
private authService = inject(AuthService);
formSubmitted = signal(false);
loginForm = this.fb.group({
  email: ['',[Validators.required, Validators.email]],
  password : ['',[Validators.required, Validators.minLength(5)]]
})

get emailControl(){
  return this.loginForm.get('email') as FormControl;
}
get passwordControl(){
  return this.loginForm.get('password') as FormControl;
}


async submit(){
this.formSubmitted.set(true);
if(this.loginForm.valid){
  const loginForm = this.loginForm.getRawValue() as loginForm;
  try{
     await this.authService.signin(loginForm);
      this.router.navigateByUrl("/dashboard");
    this.loginForm.reset();
    
  }catch(e : any){
console.log(e)
  }
}
}
}
