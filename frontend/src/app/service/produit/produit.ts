import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Produit } from '../../models/produit';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProduitsService {

  private apiUrl = 'http://localhost:8080/api/produits';

  constructor(private http: HttpClient) {}

  getProduits(): Observable<Produit[]> {
    return this.http.get<Produit[]>(this.apiUrl);
  }

  addProduit(formData: FormData): Observable<Produit> {
  return this.http.post<Produit>(`${this.apiUrl}`, formData);
}
}