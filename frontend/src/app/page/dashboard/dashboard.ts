import { Component } from '@angular/core';


import { CommonModule } from '@angular/common';
import { Sidebar } from '../../component/dashbord/sidbar/sidbar';
import { Topbar } from '../../component/dashbord/topbar/topbar';
<<<<<<< HEAD
import { RouterModule } from '@angular/router';
=======
>>>>>>> b7c2b2968028bd5fe3056a1f28e00175ae8225a6

@Component({
  selector: 'app-dashboard',
  standalone: true,
<<<<<<< HEAD
  imports: [Sidebar, Topbar, CommonModule,RouterModule],
=======
  imports: [Sidebar, Topbar, CommonModule],
>>>>>>> b7c2b2968028bd5fe3056a1f28e00175ae8225a6
  templateUrl: './dashboard.html'
})
export class Dashboard {}