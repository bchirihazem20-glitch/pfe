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
}

const U = 'https://images.unsplash.com/photo-';

@Component({
  selector: 'app-galerie',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './gallary.html',
  styleUrls: ['./gallary.css'],
})
export class Galerie {
  /** Logo rond sur chaque visuel */
  readonly badgeSrc = 'assets/image/logo_academy_2026.png';
  readonly kitTextTop = 'STAR ACADEMY';
  readonly kitTextBottom = 'MEDNINE';

  photos: GalleryPhoto[] = [
    {
      src: `https://unsplash.com/photos/U0R5_eUTTQE/download?auto=format&fit=crop&w=1400&q=88`,
      alt: 'Coach expliquant un exercice à un groupe de jeunes joueurs',
      caption: 'Briefing & consignes',
      showQr: true,
      showKitMark: true,
    },
    {
      src: `https://unsplash.com/photos/x1OSttM2ajo/download?auto=format&fit=crop&w=1400&q=88`,
      alt: 'Jeunes joueurs avant une séance sur le terrain',
      caption: 'Esprit d’équipe',
      showQr: true,
      showKitMark: true,
    },
    {
      src: `https://unsplash.com/photos/kD5u9TkMCUs/download?auto=format&fit=crop&w=1400&q=88`,
      alt: 'Groupe d’enfants jouant au football pendant un entraînement',
      caption: 'Ateliers d’entraînement',
      showKitMark: true,
    },
    {
      src: `https://unsplash.com/photos/wo8U7vfkWf8/download?auto=format&fit=crop&w=1400&q=88`,
      alt: 'Joueur travaillant la maîtrise du ballon',
      caption: 'Technique individuelle',
      showKitMark: true,
    },
    {
      src: `https://images.unsplash.com/photo-1518604666860-9ed391f76460?auto=format&fit=crop&w=1400&q=88`,
      alt: 'Jeunes joueurs courant et s entraînant ensemble sur le terrain',
      caption: 'Condition physique',
      showKitMark: true,
    },
    {
      src: `https://images.unsplash.com/photo-1508098682722-e99c643e7485?auto=format&fit=crop&w=1400&q=88`,
      alt: 'Séance collective d une académie de football sur terrain extérieur',
      caption: 'Séance collective',
      showKitMark: true,
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
