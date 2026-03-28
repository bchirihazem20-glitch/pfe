import { Routes } from '@angular/router';
import { Login } from "./page/login/login"
import { Home } from "./home/home"
import { Inscripition } from './page/inscripition/inscripition';
import { Concept } from './page/concept/concept';
import { Activite } from './page/activite/activite';
import { Contact } from './page/contact/contact';
import { Boutique } from './page/boutique/boutique';
import { guestGuard } from './guards/guest-guard';
import { Dashboard } from './page/dashboard/dashboard';
<<<<<<< HEAD
import { authGuard } from './guards/auth-guard';
import { DashboardBoutique } from './page/dashboard/dashboardboutique/dashboardboutique';
=======
>>>>>>> b7c2b2968028bd5fe3056a1f28e00175ae8225a6

export const routes: Routes = [
    {path: "login", component:Login,canActivate:[guestGuard]},
    {path: "", component:Home},
    {path: "concept", component:Concept},
    {path: "inscription", component:Inscripition,canActivate:[guestGuard]},
    {path: "activites", component:Activite},
    {path: "contact", component:Contact},
    {path: "boutique", component:Boutique},
<<<<<<< HEAD
    {path: "dashboard", component:Dashboard,canActivate:[authGuard],children:[
        {path:"boutique",component:DashboardBoutique}
    ]},
=======
    {path: "dashboard", component:Dashboard},
>>>>>>> b7c2b2968028bd5fe3056a1f28e00175ae8225a6

];
