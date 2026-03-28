import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../service/auth/auth';


@Component({
  selector: 'app-topbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './topbar.html'
})
export class Topbar {

  user: any;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.authService.getProfile().subscribe(res => {
      this.user = res;
    });
  }

  getInitials() {
<<<<<<< HEAD
  if (!this.user || !this.user.nom) return '';
  return this.user.nom.charAt(0).toUpperCase();
}
=======
    return this.user?.nom?.charAt(0).toUpperCase();
  }
>>>>>>> b7c2b2968028bd5fe3056a1f28e00175ae8225a6
}