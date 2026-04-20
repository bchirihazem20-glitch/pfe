import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Player {
  id: number;
  nom: string;
  prenom: string;
}

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  private apiUrl = 'http://localhost:8080/api/users'; 

  constructor(private http: HttpClient) {}

  getPlayersByGroupe(groupeId: number): Observable<Player[]> {
    return this.http.get<Player[]>(`${this.apiUrl}/groupe/${groupeId}`);
  }
}