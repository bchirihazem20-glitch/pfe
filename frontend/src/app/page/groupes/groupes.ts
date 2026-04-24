import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { GroupesService } from '../../service/groupes.service';
import { PlayerService } from '../../service/player.service';

@Component({
  selector: 'app-groupes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './groupes.html',
  styleUrls: ['./groupes.css']
})
export class Groupes implements OnInit {
  groupes: any[] = [];
  filteredGroupes: any[] = [];
  paginatedGroupes: any[] = [];
  loading = false;

  showModal = false;
  isEditMode = false;
  showPlayersModal = false;

  selectedGroupe: any = null;
  selectedPlayers: any[] = [];
  playersLoading = false;
  playersError = false;

  searchTerm = '';
  sortColumn = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  currentPage = 1;
  pageSize = 8;
  totalPages = 1;

  formData: any = { id: null, libelle: '', description: '' };

  constructor(
    private groupesService: GroupesService,
    private playerService: PlayerService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.getGroupes();
  }

  get pageNumbers(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i + 1);
  }

  getGroupes(): void {
    this.loading = true;
    this.groupesService.getAllGroupes().subscribe({
      next: (data: any[]) => {
        this.groupes = data || [];
        this.applyFilters();
        this.loading = false;
        this.cd.detectChanges();
      },
      error: (err: any) => {
        console.error('Erreur chargement groupes :', err);
        this.loading = false;
        this.cd.detectChanges();
      }
    });
  }

  applyFilters(): void {
    let result = [...this.groupes];

    if (this.searchTerm.trim()) {
      const term = this.searchTerm.toLowerCase();
      result = result.filter(g =>
        g.libelle?.toLowerCase().includes(term) ||
        g.description?.toLowerCase().includes(term)
      );
    }

    if (this.sortColumn) {
      result.sort((a, b) => {
        const valA = String(a[this.sortColumn] ?? '');
        const valB = String(b[this.sortColumn] ?? '');
        const cmp = valA.localeCompare(valB);
        return this.sortDirection === 'asc' ? cmp : -cmp;
      });
    }

    this.filteredGroupes = result;
    this.totalPages = Math.max(1, Math.ceil(result.length / this.pageSize));
    this.currentPage = 1;
    this.updatePage();
  }

  sortBy(column: string): void {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }
    this.applyFilters();
  }

  getSortIcon(column: string): string {
    if (this.sortColumn !== column) return '↕';
    return this.sortDirection === 'asc' ? '↑' : '↓';
  }

  changePage(page: number): void {
    this.currentPage = page;
    this.updatePage();
  }

  updatePage(): void {
    const start = (this.currentPage - 1) * this.pageSize;
    this.paginatedGroupes = this.filteredGroupes.slice(start, start + this.pageSize);
  }

  getInitials(libelle: string): string {
    if (!libelle) return '?';
    return libelle.split(' ').map(w => w[0]).slice(0, 2).join('').toUpperCase();
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
        this.cd.detectChanges();
      },
      error: (err: any) => {
        console.error('Erreur chargement joueurs :', err);
        this.playersLoading = false;
        this.playersError = true;
        this.cd.detectChanges();
      }
    });
  }

  closePlayersPopup(): void {
    this.showPlayersModal = false;
    this.selectedGroupe = null;
    this.selectedPlayers = [];
  }

  openCreateModal(): void {
    this.isEditMode = false;
    this.formData = { id: null, libelle: '', description: '' };
    this.showModal = true;
  }

  openEditModal(groupe: any): void {
    this.isEditMode = true;
    this.formData = { id: groupe.id, libelle: groupe.libelle, description: groupe.description };
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
  }

  saveGroupe(): void {
    const payload = { libelle: this.formData.libelle, description: this.formData.description };

    if (this.isEditMode) {
      this.groupesService.updateGroupe(this.formData.id, payload).subscribe({
        next: () => { this.getGroupes(); this.closeModal(); },
        error: (err: any) => console.error('Erreur modification :', err)
      });
    } else {
      this.groupesService.createGroupe(payload).subscribe({
        next: () => { this.getGroupes(); this.closeModal(); },
        error: (err: any) => console.error('Erreur création :', err)
      });
    }
  }

  deleteGroupe(id: number): void {
    if (!confirm('Voulez-vous vraiment supprimer ce groupe ?')) return;
    this.groupesService.deleteGroupe(id).subscribe({
      next: () => this.getGroupes(),
      error: (err: any) => console.error('Erreur suppression :', err)
    });
  }
}