import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProduitsService } from '../../../service/produit/produit';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-produit',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './addproduit.html'
})
export class AddProduit {

   produit = {
    nom: '',
    prix: 0,
    promo: 0,
    imageFile: null as File | null
  };

  constructor(
    private service: ProduitsService,
    private router: Router
  ) {}

  onFileSelected(event: any) {
    this.produit.imageFile = event.target.files[0];
  }

  addProduit() {
    if (!this.produit.imageFile) {
      alert('Veuillez sélectionner une image');
      return;
    }

    const formData = new FormData();
    formData.append('nom', this.produit.nom);
    formData.append('prix', this.produit.prix.toString());
    formData.append('promo', this.produit.promo.toString());
    formData.append('image', this.produit.imageFile);

    this.service.addProduit(formData).subscribe({
      next: () => {
        alert('Produit ajouté avec image');
        this.router.navigate(['/dashboard/boutique']);
      },
      error: (err) => {
        console.error(err);
        alert('Erreur lors de l\'ajout');
      }
    });
  }
}