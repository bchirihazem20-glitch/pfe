import { Routes } from '@angular/router';

import { Home } from './home/home';
import { Inscripition } from './page/inscripition/inscripition';
import { Dashboard } from './page/dashboard/dashboard';
import { DashboardBoutique } from './page/dashboard/dashboardboutique/dashboardboutique';
import { AddProduit } from './page/dashboard/addproduit/addproduit';
import { DashboardPlaceholder } from './page/dashboard/placeholder/dashboard-placeholder';
import { AnalyseComponent } from './dashbord/analyse-donneer/analyse-donneer';
import { Message } from './dashbord/message/message';
import { Groupes } from './page/groupes/groupes';
import { AdminPage } from './page/admin-page/admin-page';
import { LoginComponent } from './auth/login/login';

import { guestGuard } from './guards/guest-guard';
import { authGuard } from './guards/auth-guard';
import { adminFileGuard } from './guards/admin-file-guard';
import { Utilisateurs } from './page/utilisateurs/utilisateurs';

export const routes: Routes = [
  { path: '', component: Home },

  { path: 'login', component: LoginComponent },
  { path: 'inscription', component: Inscripition, canActivate: [guestGuard] },

{
  path: 'adminPage',
  component: AdminPage,
  children: [
    { path: '', component: AnalyseComponent },
    { path: 'groupes', component: Groupes },
    { path: 'utilisateurs', component: Utilisateurs }
  ]
},

  {
    path: 'dashboard',
    component: Dashboard,
    canActivate: [authGuard],
    children: [
      { path: '', component: AnalyseComponent, canActivate: [adminFileGuard] },
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
      { path: 'tableau-entrainement', component: DashboardPlaceholder, data: { title: "Tableau d'entraînement" } },
      { path: 'boutique', component: DashboardBoutique, canActivate: [adminFileGuard] },
      { path: 'boutique/add-produit', component: AddProduit, canActivate: [adminFileGuard] },
      { path: 'messages-direct', component: Message }
    ]
  }
];