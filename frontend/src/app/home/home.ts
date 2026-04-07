import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Header } from '../component/home/header/header';
import { Concept } from '../page/concept/concept';
import { Activite } from '../page/activite/activite';
import { Contact } from '../page/contact/contact';
import { Boutique } from '../page/boutique/boutique';
import { PlanningComponent } from '../page/planning/planning';
import { Galerie } from '../gallary/gallary';



@Component({
  selector: 'app-home',
  standalone: true,
  imports:[
    RouterModule,
    Header,
    Concept,
    Activite,
    Contact,
    Boutique,
    PlanningComponent,
    Galerie,
  ],
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
})
export class Home {

  /** Hero photo (Académie) — served from `src/assets/image` */
  readonly heroImageSrc =
    'assets/image/gemini-3.1-flash-image-preview (nano-banana-2)_a_donner_une_image_de_.png';

  readonly academyName = 'Stars';
  readonly academyHighlight = 'Academy';
  readonly academyCity = 'Médenine';

  readonly heroDescription =
    'Académie de football à Medenine formant les jeunes talents ' +
    'avec rigueur, passion et encadrement professionnel pour ' +
    'un avenir sportif prometteur.';

  readonly reviewsLabel = 'Avis des utilisateurs';
  readonly satisfactionLabel = 'de satisfaction';

  rating = 4.8;
  satisfaction = 95;

  readonly footerTagline =
    'Former – Inspirer – Révéler les talents de demain';

  readonly year = new Date().getFullYear();

}
