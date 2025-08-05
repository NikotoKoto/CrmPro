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
  :host {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: var(--section-bg);
  padding: 1rem;
}

form {
  width: 100%;
  max-width: 400px;
  background-color: #fff;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

input[type="text"],
input[type="password"] {
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
}

input:focus {
  outline: none;
  border-color: var(--primary);
  box-shadow: 0 0 0 1px var(--primary);
}

label {
  font-weight: 600;
  margin-bottom: 0.5rem;
}

button {
  padding: 0.75rem 1rem;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

button:hover {
  background-color: var(--primary-hover);
}

.error {
  color: var(--danger);
  font-size: 0.875rem;
  margin-top: 0.25rem;
}

a {
  margin-top: 1rem;
  font-size: 0.9rem;
  color: var(--primary);
  cursor: pointer;
  text-align: center;
}

a:hover {
  text-decoration: underline;
}

/* Responsive adjustments */
@media (max-width: 480px) {
  form {
    padding: 1.5rem;
    gap: 0.75rem;
  }

  h2 {
    font-size: 1.5rem;
  }

  button {
    width: 100%;
  }
}
`
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
