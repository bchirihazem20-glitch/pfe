import { Routes } from '@angular/router';
import { Login } from "./page/login/login"
import { Home } from "./home/home"
import { Inscripition } from './page/inscripition/inscripition';
import { Concept } from './page/concept/concept';
import { Activite } from './page/activite/activite';
import { Contact } from './page/contact/contact';
import { Boutique } from './page/boutique/boutique';
import { guestGuard } from './guards/guest-guard';

export const routes: Routes = [
    {path: "login", component:Login,canActivate:[guestGuard]},
    {path: "", component:Home},
    {path: "concept", component:Concept},
    {path: "inscripition", component:Inscripition,canActivate:[guestGuard]},
    {path: "activites", component:Activite},
    {path: "contact", component:Contact},
    {path: "boutique", component:Boutique},

];
