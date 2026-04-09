import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import {
  AdminAnalyticsService,
  AdminAnalyticsSummary,
} from '../../../service/admin-analytics/admin-analytics';

@Component({
  selector: 'app-analyse-donnees',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './analyse-donnees.html',
  styleUrl: './analyse-donnees.scss',
})
export class AnalyseDonnees implements OnInit {
  data: AdminAnalyticsSummary | null = null;
  loading = true;
  error: string | null = null;

  constructor(private analytics: AdminAnalyticsService) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading = true;
    this.error = null;
    this.analytics.getSummary().subscribe({
      next: (d) => {
        this.data = d;
        this.loading = false;
      },
      error: (err) => {
        console.error('Analytics', err);
        if (err.status === 403) {
          this.error =
            'Accès refusé. Cette page est réservée aux administrateurs.';
        } else {
          this.error =
            'Impossible de charger les statistiques. Vérifiez le serveur et votre connexion.';
        }
        this.loading = false;
      },
    });
  }

  /** Pourcentage pour barres (0–100), dénominateur = total utilisateurs */
  pct(value: number): number {
    if (!this.data || this.data.totalUsers <= 0) return 0;
    return Math.round((100 * value) / this.data.totalUsers);
  }
}
