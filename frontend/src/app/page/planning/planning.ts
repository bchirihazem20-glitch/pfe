import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-planning',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './planning.html',
})
export class PlanningComponent {

  planning = [
    { cat: 'U6', lundi: '-', mardi: '-', mercredi: '16:00', jeudi: '-', vendredi: '-', samedi: '10:00', dimanche: 'MATCH' },
    { cat: 'U8', lundi: '17:00', mardi: '-', mercredi: '-', jeudi: '17:00', vendredi: '-', samedi: '11:00', dimanche: 'MATCH' },
    { cat: 'U10', lundi: '18:00', mardi: '18:00', mercredi: '-', jeudi: '-', vendredi: '17:30', samedi: '14:00', dimanche: 'MATCH' },
    { cat: 'U12', lundi: '-', mardi: '18:00', mercredi: '18:00', jeudi: '-', vendredi: '-', samedi: '15:30', dimanche: 'MATCH' },
    { cat: 'U14', lundi: '17:00', mardi: '-', mercredi: '17:00', jeudi: '-', vendredi: '17:00', samedi: '12:30', dimanche: 'MATCH' },
    { cat: 'U16', lundi: '18:30', mardi: '-', mercredi: '18:30', jeudi: '-', vendredi: '18:00', samedi: '16:00', dimanche: 'MATCH' },
    { cat: 'U18', lundi: '-', mardi: '19:00', mercredi: '-', jeudi: '19:00', vendredi: '-', samedi: '17:00', dimanche: 'MATCH' },
  ];

}