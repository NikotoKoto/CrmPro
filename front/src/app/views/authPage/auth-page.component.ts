import { Component, inject, signal } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { UserForm } from '../../shared/interfaces/user.interfaces';
import { passwordValidator } from '../../shared/validators/password-match.validator';
import { Router } from '@angular/router';
import { AuthService } from '../../shared/services/auth.service';

@Component({
  selector: 'app-auth-page',
  imports: [ReactiveFormsModule],
  template: `
    <form [formGroup]="userForm" (ngSubmit)="submit()">
      <div class=" flex flex-col">
        <div class="mb-20 flex flex-col">
          <label for="name">Nom</label>
          <input formControlName="name" type="text" id="name" />
        </div>
        <div class="mb-20 flex flex-col">
          <label for="firstname">Prenom</label>
          <input formControlName="firstname" type="text" id="firstname" />
        </div>
        <div class="mb-20 flex flex-col">
          <label for="email">Email</label>
          <input formControlName="email" type="text" id="email" />
          @if(emailControl.errors?.['required'] && emailControl.touched){
          <p class="error">Email obligatoire</p>
          } @else if(emailControl.errors?.['email'] && emailControl.touched){
          <p class="error">Email non valide</p>
          }
        </div>
        <div class="mb-20 flex flex-col">
          <label for="password">Choisir un mot de passe</label>
          <input formControlName="password" type="password" id="password" />
          @if(passwordControl.errors?.['required'] && passwordControl.touched){
          <p class="error">mot de passe obligatoire</p>
          }@else if (passwordControl.errors?.['minlength'] &&
          passwordControl.touched) {
          <p class="error">taille du mot de passe trop court</p>
          }
        </div>

        <div class="flex flex-col mb-20">
          <label for="confirmPassword">Confirmer votre mot de passe</label>
          <input
            formControlName="confirmPassword"
            type="password"
            id="confirmPassword"
          />
        </div>
        <div class=" flex justify-center">
          <button class=" btn btn-primary">Sauvegarder</button>
        </div>
      </div>
    </form>
  `,
  styles: `
  :host{
    flex:1;
    display:flex;
    flex-direction: column;
    justify-content:center;
    padding: var(--spacing-md);
    align-items:center;
    
  }
  
form {
      padding: 24px;
      border-radius: 8px;
      background-color: #fff;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 400px;
    }`,
})
export class AuthPageComponent {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  authSerice = inject(AuthService);
  userForm = this.fb.group(
    {
      name: ['', Validators.required],
      firstname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(5)]],
      confirmPassword: ['', Validators.required],
    },
    {
      validators: passwordValidator(),
    }
  );
  get emailControl() {
    return this.userForm.get('email') as FormControl;
  }
  get passwordControl() {
    return this.userForm.get('password') as FormControl;
  }

  async submit() {
    if (this.userForm.invalid) return;

    const userForm: UserForm = this.userForm.getRawValue() as UserForm;
    try {
      await this.authSerice.register(userForm);
      this.router.navigateByUrl('/');
      this.userForm.reset();
    } catch (error) {
      console.error("Erreur lors de l'inscription", error);
    }
  }
}
