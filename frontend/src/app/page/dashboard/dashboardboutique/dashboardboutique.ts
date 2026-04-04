import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ProduitsService } from '../../../service/produit/produit';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard-boutique',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboardboutique.html',
  styleUrls: ['./dashboardboutique.scss']
})
export class DashboardBoutique implements OnInit {

  produits: any[]       = [];
  loading               = true;
  error: string | null  = null;
  produitToDelete: any  = null;

  /** Préfixe du serveur pour les images (adapter si besoin) */
  readonly baseUrl = 'http://localhost:8080';

  constructor(
    private service: ProduitsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadProduits();
  }

  /* ──────────────── DATA ──────────────── */

  loadProduits(): void {
    this.loading = true;
    this.error   = null;

    this.service.getProduits().subscribe({
      next: (data) => {
        this.produits = data;
        this.loading  = false;
      },
      error: (err) => {
        console.error('Erreur chargement produits', err);
        this.error   = 'Impossible de charger les produits. Vérifiez votre connexion.';
        this.loading = false;
      }
    });
  }

  /* ──────────────── NAVIGATION ──────────────── */

  goToAdd(): void {
    this.router.navigate(['dashboard/boutique/add-produit']);
  }

  goToEdit(id: number): void {
    this.router.navigate(['dashboard/boutique/edit-produit', id]);
  }

  /* ──────────────── DELETE ──────────────── */

  confirmDelete(produit: any): void {
    this.produitToDelete = produit;
  }

  cancelDelete(): void {
    this.produitToDelete = null;
  }



  /* ──────────────── HELPERS ──────────────── */

  getPrixApresPromo(p: any): number {
    return p.prix - (p.prix * p.promo) / 100;
  }

  onImgError(event: Event): void {
    (event.target as HTMLImageElement).style.display = 'none';
  }
}