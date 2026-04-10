import { ChangeDetectorRef, Component } from '@angular/core';
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

  constructor(private authService: AuthService,private cd: ChangeDetectorRef) {}

  ngOnInit() {
    this.authService.getProfile().subscribe(res => {
      this.user = res;
      this.cd.detectChanges();
    });
  }

  getInitials() {
  if (!this.user || !this.user.nom) return '';
  return this.user.nom.charAt(0).toUpperCase();
}
}