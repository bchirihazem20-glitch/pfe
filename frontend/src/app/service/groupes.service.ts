import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Groupe {
  id: number;
  libelle: string;
  description: string;
  nombreUsers?: number;
}

@Injectable({
  providedIn: 'root'
})
export class GroupesService {
  private apiUrl = 'http://localhost:8080/api/groupes';

  constructor(private http: HttpClient) {}

  getAllGroupes(): Observable<Groupe[]> {
    return this.http.get<Groupe[]>(this.apiUrl);
  }

  createGroupe(data: { libelle: string; description: string }): Observable<Groupe> {
    return this.http.post<Groupe>(this.apiUrl, data);
  }

  updateGroupe(id: number, data: { libelle: string; description: string }): Observable<Groupe> {
    return this.http.put<Groupe>(`${this.apiUrl}/${id}`, data);
  }

  deleteGroupe(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
  }
}