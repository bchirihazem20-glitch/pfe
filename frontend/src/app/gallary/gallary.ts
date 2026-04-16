import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface GalleryPhoto {
  src: string;
  alt: string;
  caption: string;
  /** Pastille type QR (décoratif), comme sur ta maquette */
  showQr?: boolean;
  /** Marquage tenue (logo + texte) sur la photo */
  showKitMark?: boolean;
  /** Teinte bleu/blanc (filtre CSS sur la photo d’origine) */
  blueWhiteLook?: boolean;
  /** Marquage bas-droite façon filigrane académie */
  kitMarkBottomRight?: boolean;
}

@Component({
  selector: 'app-galerie',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './gallary.html',
  styleUrls: ['./gallary.css'],
})
export class Galerie {
  /** Logo officiel (Stars Academy Mednine) */
  readonly badgeSrc = 'assets/image/logo_stars_academy_mednine.png';
  readonly kitTextTop = 'STAR ACADEMY';
  readonly kitTextBottom = 'MEDNINE';

  /** Visuels orientés “équipe / académie” avec tenue homogène ; teinte unifiée via CSS (bleu & blanc). */
  photos: GalleryPhoto[] = [
    {
      src: 'assets/image/img3.png',
      alt: 'Jeunes joueurs Stars Academy Mednine — esprit d’équipe sur le terrain',
      caption: 'Photo académie — tenue d’entraînement',
      showKitMark: true,
      blueWhiteLook: true,
      kitMarkBottomRight: true,
    },
    {
      src: 'assets/image/gallery_training_academy.png',
      alt: 'Équipe de football posant en photo de groupe avec maillots assortis',
      caption: 'Photo officielle — même kit académie',
      showKitMark: true,
      blueWhiteLook: true,
    },
    {
      src: 'assets/image/img4.jpg',
      alt: 'Équipe célébrant sur le terrain en tenue identique',
      caption: 'Collectif & célébration',
      showKitMark: true,
      blueWhiteLook: true,
    },
    {
      src: 'assets/image/img5.jpg',
      alt: 'Séance d’entraînement football — joueurs en tenue de club',
      caption: 'Séance terrain',
      showKitMark: true,
      blueWhiteLook: true,
    },
    {
      src: 'https://images.unsplash.com/photo-1431324155629-1a6deb1dec8d?auto=format&fit=crop&w=1600&q=85',
      alt: 'Stade et pelouse — ambiance académie football',
      caption: 'Infrastructure & pelouse',
      showKitMark: true,
      blueWhiteLook: true,
    },
    {
      src: 'assets/image/img12.jpg',
      alt: 'Ballon de football sur la ligne — préparation séance',
      caption: 'Préparation & focus',
      showKitMark: true,
      blueWhiteLook: true,
    },
    {
      src: 'assets/image/img9.png',
      alt: 'Match ou entraînement — joueurs en maillots de même équipe',
      caption: 'Jeu collectif',
      showKitMark: true,
      blueWhiteLook: true,
    },
    {
      src: 'assets/image/img8.png',
      alt: 'Football sur terrain — dynamique d’équipe',
      caption: 'Intensité & discipline',
      showKitMark: true,
      blueWhiteLook: true,
    },
  ];

  selected: GalleryPhoto | null = null;

  openImage(photo: GalleryPhoto): void {
    this.selected = photo;
  }

  closeImage(): void {
    this.selected = null;
  }
}
