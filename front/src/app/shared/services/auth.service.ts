import { Router } from '@angular/router';
import { loginForm, User } from './../interfaces/user.interfaces';
import { computed, inject, Injectable, resource, signal } from '@angular/core'; // ⬅️ signal
import { UserForm } from '../interfaces/user.interfaces';

const API_AUTH = 'http://localhost:8080/auth';
const API_USER = 'http://localhost:8080/users';

@Injectable({ providedIn: 'root' })
export class AuthService {
  // 🔔 signal pour notifier les autres services/pages d’un changement d’auth
  authChanged = signal<number>(0);

  currentUserResource = resource({
    loader: () => this.fetchCurrentUser(),
  });

  isLoggedin = computed(() => {
    const value = this.currentUserResource.value();
    return value !== undefined ? !!value : undefined;
  });

  currentUser = computed(() => this.currentUserResource.value());
  private router = inject(Router);

  async fetchCurrentUser() {
    const response = await fetch(`${API_USER}/me`, {
      credentials: 'include',
      cache: 'no-store',
      headers: { 'Cache-Control': 'no-cache' }
    });
    if (!response.ok) return undefined;
    return await response.json();
  }

  async register(user: UserForm): Promise<User> {
    try {
      const response = await fetch(`${API_AUTH}/register`, {
        method: 'POST',
        headers: { 'Content-type': 'application/json', 'Cache-Control': 'no-cache' },
        body: JSON.stringify(user),
        credentials: 'include',
        cache: 'no-store'
      });
      if (!response.ok) throw new Error('Oops');

      const data = await response.json();
      this.currentUserResource.reload();
      this.authChanged.set(Date.now());     // 🔔 
      return data;
    } catch {
      throw new Error('Oops');
    }
  }

  async signin(loginForm: loginForm): Promise<User> {
    try {
      const response = await fetch(`${API_AUTH}/login`, {
        method: 'POST',
        credentials: 'include',
        headers: { 'Content-type': 'application/json', 'Cache-Control': 'no-cache' },
        body: JSON.stringify(loginForm),
        cache: 'no-store'
      });
      if (!response.ok) throw new Error('Oops');

      const data = await response.json();
      this.currentUserResource.reload();
      this.authChanged.set(Date.now());     // 🔔
      return data as User;
    } catch {
      throw new Error('Oops');
    }
  }

  async loggout() {
    await fetch(`${API_AUTH}/logout`, {
      method: 'DELETE',
      credentials: 'include',
      cache: 'no-store',
      headers: { 'Cache-Control': 'no-cache' }
    });
    this.currentUserResource.reload();
    this.authChanged.set(Date.now());       // 🔔
    this.router.navigateByUrl('/signIn');
  }

  constructor() {}
}
