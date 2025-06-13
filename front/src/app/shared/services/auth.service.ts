import { Router } from '@angular/router';
import { loginForm, User } from './../interfaces/user.interfaces';

import { computed, inject, Injectable, resource } from '@angular/core';
import { UserForm } from '../interfaces/user.interfaces';

const API_AUTH = 'http://localhost:8080/auth';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  currentUserResource = resource({
    loader: () => this.fetchCurrentUser(),
  });

  isLoggedin = computed(() => {
    const value = this.currentUserResource.value();
    if (value !== undefined) {
      return !!value;
    } else {
      return undefined;
    }
  });

  currentUser = computed(() => this.currentUserResource.value());
  private router = inject(Router);

  async fetchCurrentUser() {
  const response = await fetch(`${API_AUTH}/current`, {
    credentials: 'include' 
  });
  if(!response.ok){
    return undefined;
  }
  return await response.json();
}

  async register(user: UserForm): Promise<User> {
    try {
      const response = await fetch(`${API_AUTH}/register`, {
        method: 'POST',
        headers: {
          'Content-type': 'application/json',
        },
        body: JSON.stringify(user),
        credentials:'include',
      });
      if (response.ok) {
        const data = await response.json();
        this.currentUserResource.reload();
        return data;
      } else {
        throw new Error('Oops');
      }
    } catch {
      throw new Error('Oops');
    }
  }

  async signin(loginForm: loginForm): Promise<User> {
    try {
      const response = await fetch(`${API_AUTH}/login`, {
        method: 'POST',
         credentials: 'include',
        headers: {
          'Content-type': 'application/json',
        },
        body: JSON.stringify(loginForm),
      });
      if (response.ok) {
        const data = await response.json();
        this.currentUserResource.reload();
        return data as User;
      } else {
        throw new Error('Oops');
      }
    } catch (e) {
      throw new Error('Oops');
    }
  }

  async loggout(){
    await fetch(`${API_AUTH}/logout`,{
      method:'DELETE',
      credentials:'include'
    });
    this.currentUserResource.reload();
    this.router.navigateByUrl("/signIn")
  }

  constructor() {}
}
