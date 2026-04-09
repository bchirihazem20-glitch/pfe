import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../service/auth/auth';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterModule, MatIconModule, CommonModule],
  templateUrl: './sidbar.html',
})
export class Sidebar implements OnInit {
  user: { role?: string } | null = null;

  constructor(private auth: AuthService, private cd: ChangeDetectorRef) {}

  
  ngOnInit(): void {
  this.auth.getProfile().subscribe({
    next: (u) => {
      this.user = u;
      console.log(u);
      this.cd.detectChanges(); // 🔥 الحل
    },
    error: () => (this.user = null),
  });
}
}