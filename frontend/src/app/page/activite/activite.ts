import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Header } from '../../component/home/header/header';

@Component({
  selector: 'app-activite',
  standalone: true,
  imports: [CommonModule, Header],
  templateUrl: './activite.html',
  styleUrl: './activite.css',
})
export class Activite {
  @Input() showHeader = true;
}
