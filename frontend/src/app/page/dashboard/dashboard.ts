import { Component } from '@angular/core';


import { CommonModule } from '@angular/common';
import { Sidebar } from '../../component/dashbord/sidbar/sidbar';
import { Topbar } from '../../component/dashbord/topbar/topbar';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [Sidebar, Topbar, CommonModule,RouterModule],

templateUrl : './dashboard.html'
})
export class Dashboard {}