import { Component, Input } from '@angular/core';
import { Header } from '../../component/home/header/header';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-boutique',
  standalone: true,
  imports: [Header, CommonModule],
  templateUrl: './boutique.html',
  styleUrl: './boutique.css',
})
export class Boutique {
  @Input() showHeader = true;

   produits = [
    {
      id: 1,
      nom: 'Maillot Académie',
      prix: 80,
      promo: 10,
      image: 'assets/produits/maillot.jpg'
    },
    {
      id: 2,
      nom: 'Ballon Football',
      prix: 50,
      promo: 0,
      image: 'assets/produits/ballon.jpg'
    },
    {
      id: 3,
      nom: 'Chaussures Sport',
      prix: 120,
      promo: 20,
      image: 'assets/produits/chaussure.jpg'
    }
  ];

  acheter(produit: any) {
    alert("Produit acheté: " + produit.nom);
  }
}

