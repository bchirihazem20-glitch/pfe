import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Header } from '../../component/home/header/header';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [CommonModule, Header],
  templateUrl: './contact.html',
  styleUrl: './contact.css',
})
export class Contact {
  @Input() showHeader = true;
}
