import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { ProduitsService } from '../../../service/produit/produit';

@Component({
  selector: 'app-add-produit',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './addproduit.html',
  styleUrls: ['./addproduit.scss']
})
export class AddProduit {

  produit = {
    nom:        '',
    prix:       0,
    promo:      0,
    categorie:  '',
    imageFile:  null as File | null
  };

  imagePreview: string | null = null;
  isDragOver  = false;
  loading     = false;
  errorMsg    = '';

  constructor(
    private service: ProduitsService,
    private router:  Router
  ) {}

  /* ── Image Handling ─────────────────────────────────── */

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) this.setFile(file);
  }

  onDragOver(event: DragEvent): void {
    event.preventDefault();
    this.isDragOver = true;
  }

  onDragLeave(): void {
    this.isDragOver = false;
  }

  onDrop(event: DragEvent): void {
    event.preventDefault();
    this.isDragOver = false;
    const file = event.dataTransfer?.files[0];
    if (file && file.type.startsWith('image/')) this.setFile(file);
  }

  private setFile(file: File): void {
    this.produit.imageFile = file;
    const reader = new FileReader();
    reader.onload = (e) => this.imagePreview = e.target?.result as string;
    reader.readAsDataURL(file);
  }

  removeFile(): void {
    this.produit.imageFile = null;
    this.imagePreview      = null;
  }

  /* ── Helpers ────────────────────────────────────────── */

  getPrixFinal(): number {
    return this.produit.prix - (this.produit.prix * this.produit.promo) / 100;
  }

  /* ── Submit ─────────────────────────────────────────── */

  addProduit(): void {
    this.errorMsg = '';

    if (!this.produit.nom.trim()) {
      this.errorMsg = 'Le nom du produit est requis.'; return;
    }
    if (this.produit.prix <= 0) {
      this.errorMsg = 'Le prix doit être supérieur à 0.'; return;
    }
    if (!this.produit.imageFile) {
      this.errorMsg = 'Veuillez sélectionner une image.'; return;
    }

    this.loading = true;

    const formData = new FormData();
    formData.append('nom',       this.produit.nom);
    formData.append('prix',      this.produit.prix.toString());
    formData.append('promo',     this.produit.promo.toString());
    formData.append('categorie', this.produit.categorie);
    formData.append('image',     this.produit.imageFile);

    this.service.addProduit(formData).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/dashboard/boutique']);
      },
      error: (err) => {
        console.error(err);
        this.loading  = false;
        this.errorMsg = 'Erreur lors de l\'ajout du produit. Réessayez.';
      }
    });
  }
}