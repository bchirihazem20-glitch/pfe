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
import { authGuard } from './guards/auth-guard';
import { DashboardBoutique } from './page/dashboard/dashboardboutique/dashboardboutique';
import { PlanningComponent } from './page/planning/planning';
import { AddProduit } from './page/dashboard/addproduit/addproduit';
import { adminFileGuard } from './guards/admin-file-guard';

export const routes: Routes = [
    {path: "login", component:Login,canActivate:[guestGuard]},
    {path: "", component:Home},
    {path: "concept", component:Concept},
    {path: "inscription", component:Inscripition,canActivate:[guestGuard]},
    {path: "activites", component:Activite},
    {path: "contact", component:Contact},
    {path: "boutique", component:Boutique},
    {path: "planning", component:PlanningComponent},
    {path: "dashboard", component:Dashboard,canActivate:[authGuard],children:[
      {path:"boutique",component:DashboardBoutique,canActivate:[adminFileGuard]},
      {path:"boutique/add-produit",component:AddProduit,canActivate:[adminFileGuard]}

    ]},

];
