import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth';
  private userSubject = new BehaviorSubject<any>(null);
  user$ = this.userSubject.asObservable();

  constructor(private http: HttpClient) { }

  register(userData: any): Observable<any> {
    return this.http.post(this.apiUrl + "/register", userData);
  }
  login(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl + "/login", data).pipe(
      tap(res => {
        if (res?.token) {
          localStorage.setItem('token', res.token);
        }
      })
    );
  }

  getProfile(): Observable<any> {
    return this.http.get<any>(this.apiUrl + "/profile").pipe(
      tap(user => this.userSubject.next(user))
    );
  }

  logout() {
    localStorage.removeItem('token');
    this.userSubject.next(null);
  }
}