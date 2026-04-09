import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-analyse',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './analyse-donneer.html',
})
export class AnalyseComponent implements OnInit {

  stats = {
    totalJoueurs: 120,
    totalCoachs: 8,
    revenus: 15000,
    abonnements: 95
  };

  joueursParMois = [
    { mois: 'Jan', value: 20 },
    { mois: 'Fév', value: 30 },
    { mois: 'Mar', value: 50 },
    { mois: 'Avr', value: 70 },
  ];

  paiements = [
    { mois: 'Jan', montant: 2000 },
    { mois: 'Fév', montant: 3500 },
    { mois: 'Mar', montant: 4000 },
    { mois: 'Avr', montant: 5500 },
  ];

  constructor() {}

  ngOnInit(): void {}
}