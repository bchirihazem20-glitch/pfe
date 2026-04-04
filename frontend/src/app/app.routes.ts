import { Routes } from '@angular/router';
import { Login } from "./page/login/login"
import { Home } from "./home/home"
import { Inscripition } from './page/inscripition/inscripition';
import { guestGuard } from './guards/guest-guard';
import { Dashboard } from './page/dashboard/dashboard';
import { authGuard } from './guards/auth-guard';
import { DashboardBoutique } from './page/dashboard/dashboardboutique/dashboardboutique';
import { AddProduit } from './page/dashboard/addproduit/addproduit';
import { adminFileGuard } from './guards/admin-file-guard';

export const routes: Routes = [
    {path: "login", component:Login,canActivate:[guestGuard]},
    {path: "", component:Home},
    {path: "inscription", component:Inscripition,canActivate:[guestGuard]},
    {path: "dashboard", component:Dashboard,canActivate:[authGuard],children:[
      {path:"boutique",component:DashboardBoutique,canActivate:[adminFileGuard]},
      {path:"boutique/add-produit",component:AddProduit,canActivate:[adminFileGuard]}

    ]},

];
