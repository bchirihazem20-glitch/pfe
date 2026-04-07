import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-galerie',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './galerie.html',
  styleUrls: ['./galerie.css']
})
export class Galerie {

  images = [
    'assets/image/g1.jpg',
    'assets/image/g2.jpg',
    'assets/image/g3.jpg',
    'assets/image/g4.jpg',
    'assets/image/g5.jpg',
    'assets/image/g6.jpg'
  ];

  selectedImage: string | null = null;

  openImage(img: string) {
    this.selectedImage = img;
  }

  closeImage() {
    this.selectedImage = null;
  }
}