import { Routes } from '@angular/router';
import { Login } from "./page/login/login"
import { Home } from "./home/home"
import { Inscripition } from './page/inscripition/inscripition';
import { Concept } from './page/concept/concept';
import { Activite } from './page/activite/activite';
import { Contact } from './page/contact/contact';
import { Boutique } from './page/boutique/boutique';

export const routes: Routes = [
    {path: "login", component:Login},
    {path: "", component:Home},
    {path: "concept", component:Concept},
    {path: "inscripition", component:Inscripition},
    {path: "activites", component:Activite},
    {path: "contact", component:Contact},
    {path: "boutique", component:Boutique},

];
