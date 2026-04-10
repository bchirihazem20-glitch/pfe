import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-tableau-entrainement',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './emploi.html',
})
export class TableauEntrainementComponent {

  groupes = [
    {
      nom: 'U10',
      coach: 'Coach Ali',
      seances: [
        { jour: 'Lundi', heure: '16:00', terrain: 'Terrain A' },
        { jour: 'Mercredi', heure: '16:00', terrain: 'Terrain A' },
      ]
    },
    {
      nom: 'U15',
      coach: 'Coach Sami',
      seances: [
        { jour: 'Mardi', heure: '17:00', terrain: 'Terrain B' },
        { jour: 'Jeudi', heure: '17:00', terrain: 'Terrain B' },
      ]
    },
    {
      nom: 'Senior',
      coach: 'Coach Karim',
      seances: [
        { jour: 'Lundi', heure: '18:00', terrain: 'Terrain C' },
        { jour: 'Vendredi', heure: '18:00', terrain: 'Terrain C' },
      ]
    }
  ];

}