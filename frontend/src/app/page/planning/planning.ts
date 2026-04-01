import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-planning',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './planning.html'
})
export class PlanningComponent {

  planning = [
    { categorie: 'U10', lundi: '18:00', mardi: '18:00', mercredi: '-', jeudi: '-', vendredi: '17:30', samedi: '14:00', dimanche: '-' },
    { categorie: 'U12', lundi: '-', mardi: '-', mercredi: '18:00', jeudi: '-', vendredi: '-', samedi: '12:30', dimanche: '09:30' },
    { categorie: 'U14', lundi: '-', mardi: '-', mercredi: '-', jeudi: '-', vendredi: '-', samedi: '15:30', dimanche: '08:00' },
    { categorie: 'U16', lundi: '-', mardi: '-', mercredi: '-', jeudi: '-', vendredi: '-', samedi: '17:00', dimanche: '11:00' }
  ];

}