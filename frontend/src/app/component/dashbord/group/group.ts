import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

interface Groupe {
  id: number;
  nom: string;
  age: string;
  nombreJoueurs: number;
}

@Component({
  selector: 'app-groupes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './group.html',
})
export class GroupesComponent {

  groupes: Groupe[] = [
    { id: 1, nom: 'Débutants', age: 'U4', nombreJoueurs: 10 },
    { id: 2, nom: 'Mini Foot', age: 'U8', nombreJoueurs: 14 },
    { id: 3, nom: 'Cadets', age: 'U12', nombreJoueurs: 18 },
    { id: 4, nom: 'Juniors', age: 'U16', nombreJoueurs: 20 },
  ];

  ajouterGroupe() {
    alert('Ajouter groupe');
  }

  modifier(groupe: Groupe) {
    alert('Modifier ' + groupe.nom);
  }

  supprimer(id: number) {
    this.groupes = this.groupes.filter(g => g.id !== id);
  }
}