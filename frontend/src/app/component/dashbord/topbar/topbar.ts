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
    return this.user?.nom?.charAt(0).toUpperCase();
  }
}