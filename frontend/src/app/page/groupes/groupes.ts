import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { GroupesService } from '../../service/groupes.service';
import { PlayerService } from '../../service/player.service';
import { Header } from '../../component/home/header/header';

@Component({
  selector: 'app-groupes',
  standalone: true,
  imports: [CommonModule, FormsModule, Header],
  templateUrl: './groupes.html',
  styleUrls: ['./groupes.css']
})
export class Groupes implements OnInit {
  loading = false;
  showModal = false;
  isEditMode = false;

  badgeSrc = 'assets/image/logo_stars_academy_mednine.png';

  groupes: any[] = [];

  formData: any = {
    id: null,
    libelle: '',
    description: ''
  };

  // Popup joueurs
  showPlayersModal = false;
  selectedGroupe: any = null;
  selectedPlayers: any[] = [];
  playersLoading = false;
  playersError = false;

  constructor(
    private groupesService: GroupesService,
    private playerService: PlayerService
  ) {}

  ngOnInit(): void {
    this.getGroupes();
  }

  getGroupes(): void {
    this.loading = true;

    this.groupesService.getAllGroupes().subscribe({
      next: (data: any[]) => {
        this.groupes = data || [];
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des groupes :', err);
        this.loading = false;
      }
    });
  }

  openPlayersPopup(groupe: any): void {
    this.selectedGroupe = groupe;
    this.selectedPlayers = [];
    this.playersLoading = true;
    this.playersError = false;
    this.showPlayersModal = true;

    this.playerService.getPlayersByGroupe(groupe.id).subscribe({
      next: (data: any[]) => {
        this.selectedPlayers = data || [];
        this.playersLoading = false;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des joueurs :', err);
        this.selectedPlayers = [];
        this.playersLoading = false;
        this.playersError = true;
      }
    });
  }

  closePlayersPopup(): void {
    this.showPlayersModal = false;
    this.selectedGroupe = null;
    this.selectedPlayers = [];
    this.playersLoading = false;
    this.playersError = false;
  }

  openCreateModal(): void {
    this.isEditMode = false;
    this.formData = {
      id: null,
      libelle: '',
      description: ''
    };
    this.showModal = true;
  }

  openEditModal(groupe: any): void {
    this.isEditMode = true;
    this.formData = {
      id: groupe.id,
      libelle: groupe.libelle,
      description: groupe.description
    };
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
  }

  saveGroupe(): void {
    const payload = {
      libelle: this.formData.libelle,
      description: this.formData.description
    };

    if (this.isEditMode) {
      this.groupesService.updateGroupe(this.formData.id, payload).subscribe({
        next: () => {
          this.getGroupes();
          this.closeModal();
        },
        error: (err) => {
          console.error('Erreur lors de la modification du groupe :', err);
        }
      });
    } else {
      this.groupesService.createGroupe(payload).subscribe({
        next: () => {
          this.getGroupes();
          this.closeModal();
        },
        error: (err) => {
          console.error('Erreur lors de la création du groupe :', err);
        }
      });
    }
  }

  deleteGroupe(id: number): void {
    this.groupesService.deleteGroupe(id).subscribe({
      next: () => {
        this.getGroupes();
      },
      error: (err) => {
        console.error('Erreur lors de la suppression du groupe :', err);
      }
    });
  }
}