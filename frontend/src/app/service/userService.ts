import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  // 🔥 GET COACHS
  getCoachs() {
    return this.http.get<any[]>(`${this.baseUrl}/coachs`);
  }

  // 🔥 ADD
  add(user: any) {
    return this.http.post<any>(this.baseUrl, user);
  }

  // 🔥 UPDATE
  update(id: number, user: any) {
    return this.http.put<any>(`${this.baseUrl}/${id}`, user);
  }

  // 🔥 DELETE
  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}