import { Component } from '@angular/core';


import { CommonModule } from '@angular/common';
import { Sidebar } from '../../component/dashbord/sidbar/sidbar';
import { Topbar } from '../../component/dashbord/topbar/topbar';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [Sidebar, Topbar, CommonModule],
  templateUrl: './dashboard.html'
})
export class Dashboard {}