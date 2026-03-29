import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import {  ProduitsService } from "../../../service/produit/produit";
import { Router, RouterModule } from "@angular/router";

@Component({
  selector: 'app-dashboard-boutique',
  imports:[CommonModule,RouterModule],
  templateUrl: './dashboardboutique.html'
})
export class DashboardBoutique {
   produits: any[] = [];

  constructor(private service: ProduitsService,private router:Router) {}



  ngOnInit() {
    this.service.getProduits().subscribe(data => {
      this.produits = data;
    });
  }
   goToAdd() {
    this.router.navigate(['dashboard/boutique/add-produit']);
  }
}