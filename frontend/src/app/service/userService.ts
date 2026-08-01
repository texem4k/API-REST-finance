import {User} from '../Model/User';
import {Observable} from 'rxjs';
import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../app.config';

@Injectable({providedIn: 'root'})
export class UserService {
  private http = inject(HttpClient);
  private baseUrl = `${API_URL}/usuarios`;

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.baseUrl, user);
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/userlist`);
  }

  findByEmail(email: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/user-email`, {
      params: { email }
    });
  }
}
