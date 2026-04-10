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
import { DashboardPlaceholder } from './page/dashboard/placeholder/dashboard-placeholder';
import { AnalyseComponent } from './dashbord/analyse-donneer/analyse-donneer';
import { Message } from './dashbord/message/message';


export const routes: Routes = [
    {path: "login", component:Login,canActivate:[guestGuard]},
    {path: "", component:Home},
    {path: "inscription", component:Inscripition,canActivate:[guestGuard]},
    {path: "dashboard", component:Dashboard,canActivate:[authGuard],children:[
      { path: '', component: AnalyseComponent , canActivate: [adminFileGuard] },
      { path: 'emploi', component: DashboardPlaceholder, data: { title: 'Emploi' } },
      { path: 'paiement', component: DashboardPlaceholder, data: { title: 'Paiement' } },
      { path: 'messages', component: DashboardPlaceholder, data: { title: 'Message' } },
      { path: 'profil', component: DashboardPlaceholder, data: { title: 'Profil' } },
      { path: 'statistiques', component: DashboardPlaceholder, data: { title: 'Statistiques' } },
      { path: 'joueurs', component: DashboardPlaceholder, data: { title: 'Liste des joueurs' } },
      { path: 'entrainements', component: DashboardPlaceholder, data: { title: 'Gestion entraînements' } },
      { path: 'performance', component: DashboardPlaceholder, data: { title: 'Suivi performance' } },
      { path: 'presence', component: DashboardPlaceholder, data: { title: 'Présence' } },
      { path: 'rapports', component: DashboardPlaceholder, data: { title: 'Rapports' } },
      { path: 'utilisateurs', component: DashboardPlaceholder, data: { title: 'Liste utilisateurs' } },
      { path: 'tableau-entrainement', component: DashboardPlaceholder, data: { title: 'Tableau d\'entraînement' } },
      { path: 'groupes', component: DashboardPlaceholder, data: { title: 'Groupes' } },
      {path:"boutique",component:DashboardBoutique,canActivate:[adminFileGuard]},
      {path:"boutique/add-produit",component:AddProduit,canActivate:[adminFileGuard]},
      {path: "messages",component: Message},
    


    ]},

];
