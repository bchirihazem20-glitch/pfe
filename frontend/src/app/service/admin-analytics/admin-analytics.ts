import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AdminAnalyticsSummary {
  totalUsers: number;
  joueurs: number;
  coachs: number;
  admins: number;
  autres: number;
  totalProduits: number;
}

@Injectable({
  providedIn: 'root',
})
export class AdminAnalyticsService {
  private readonly apiUrl = 'http://localhost:8080/api/admin/analytics';

  constructor(private http: HttpClient) {}

  getSummary(): Observable<AdminAnalyticsSummary> {
    return this.http.get<AdminAnalyticsSummary>(`${this.apiUrl}/summary`);
  }
}
