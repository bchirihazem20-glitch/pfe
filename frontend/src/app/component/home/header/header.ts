import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../service/auth/auth';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header.html',
})
export class Header {

  user: any = null;
  menuOpen = false;

  constructor(private authService: AuthService, private cd: ChangeDetectorRef) {}

  ngOnInit() {
    this.loadUser();
  }

  loadUser() {
    this.authService.getProfile().subscribe({
      next: (data) => {
        this.user = data;
        this.cd.detectChanges();
      },
      error: () => this.user = null
    });
  }

  getInitials(): string {
    if (!this.user) return '';
    return this.user.nom.charAt(0).toUpperCase();
  }

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  logout() {
    this.authService.logout();
    this.user = null;
    this.menuOpen = false;
  }
}