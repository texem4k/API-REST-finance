import { Injectable } from '@angular/core';
import { User } from '../Model/User';

@Injectable({ providedIn: 'root' })
export class SessionService {
  private readonly KEY = 'usuarioActual';

  saveUser(user: User): void {
    localStorage.setItem(this.KEY, JSON.stringify(user));
  }

  getUser(): User | undefined {
    const data = localStorage.getItem(this.KEY);
    return data ? JSON.parse(data) : undefined
      ;
  }

  logOut(): void {
    localStorage.removeItem(this.KEY);
  }

  isSesionActive(): boolean {
    return this.getUser() !== null;
  }
}
