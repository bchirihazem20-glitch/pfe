import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-planning',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './planning.html',
  styleUrls: ['./planning.css']
})
export class PlanningComponent {
 
  planning = [
    { jour: 'Lundi', activite: 'Football U10', heure: '16:00 - 17:30' },
    { jour: 'Mardi', activite: 'Fitness U12', heure: '16:00 - 17:30' },
    { jour: 'Mercredi', activite: 'Football U14', heure: '17:00 - 18:30' },
    { jour: 'Jeudi', activite: 'Technique Avancée', heure: '16:00 - 17:30' },
    { jour: 'Vendredi', activite: 'Match Amical', heure: '17:00 - 18:30' },
  ];
}