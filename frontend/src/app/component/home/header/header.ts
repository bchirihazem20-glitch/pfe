import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../service/auth/auth';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header implements OnInit {
  user: any = null;
  menuOpen = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadUser();
  }

  loadUser(): void {
    const token = localStorage.getItem('token');

    if (!token) {
      this.user = null;
      this.cd.detectChanges();
      return;
    }

    this.authService.getProfile().subscribe({
      next: (data: any) => {
        this.user = data;
        this.cd.detectChanges();
      },
      error: () => {
        this.user = null;
        localStorage.removeItem('token');
        this.cd.detectChanges();
      }
    });
  }

  getInitials(): string {
    if (!this.user?.nom) return '';
    return this.user.nom.charAt(0).toUpperCase();
  }

  toggleMenu(): void {
    this.menuOpen = !this.menuOpen;
  }

  logout(): void {
    this.authService.logout();

    localStorage.removeItem('token');
    localStorage.removeItem('user');

    this.user = null;
    this.menuOpen = false;

    this.cd.detectChanges();
    this.router.navigate(['/login']);
  }
}