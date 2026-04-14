import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

interface User {
  id: number;
  nom: string;
  email: string;
  status: 'en_attente' | 'valide' | 'refuse';
}

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './liste.html',
  styleUrls: ['./liste.css']
})
export class UsersComponent {

  users: User[] = [
    { id: 1, nom: 'Ali', email: 'ali@gmail.com', status: 'en_attente' },
    { id: 2, nom: 'Sami', email: 'sami@gmail.com', status: 'valide' },
    { id: 3, nom: 'Mouna', email: 'mouna@gmail.com', status: 'refuse' },
  ];

  valider(id: number) {
    const user = this.users.find(u => u.id === id);
    if (user) user.status = 'valide';
  }

  refuser(id: number) {
    const user = this.users.find(u => u.id === id);
    if (user) user.status = 'refuse';
  }

  consulter(user: User) {
    alert('Nom: ' + user.nom + '\nEmail: ' + user.email);
  }

  modifier(user: User) {
    alert('Modifier: ' + user.nom);
  }

  supprimer(id: number) {
    if (confirm('Supprimer cet utilisateur ?')) {
      this.users = this.users.filter(u => u.id !== id);
    }
  }
}