import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Header } from '../../component/home/header/header';

@Component({
  selector: 'app-concept',
  standalone: true,
  imports: [CommonModule, Header],
  templateUrl: './concept.html',
  styleUrls: ['./concept.css'],
})
export class Concept {
  /** When embedded in the home page, hide the duplicate navbar. */
  @Input() showHeader = true;
}