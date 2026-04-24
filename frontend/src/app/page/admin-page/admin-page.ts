import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { Header } from '../../component/home/header/header';

@Component({
  selector: 'app-admin-page',
  standalone: true,
  imports: [RouterModule, Header, RouterOutlet],
  templateUrl: './admin-page.html',
  styleUrl: './admin-page.css'
})
export class AdminPage {

  constructor(private router: Router) {}

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/']);
  }

  goToGroupes() {
    this.router.navigate(['/adminPage/groupes']);
  }
goToUtilisateurs() {
  this.router.navigate(['/adminPage/utilisateurs']);
}
}