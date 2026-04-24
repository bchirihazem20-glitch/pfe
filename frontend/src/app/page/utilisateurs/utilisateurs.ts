import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { finalize } from 'rxjs';
import { UtilisateursService } from '../../service/utilisateurs.service';

@Component({
  selector: 'app-utilisateurs',
  standalone: true,
  imports: [CommonModule, FormsModule, DatePipe],
  templateUrl: './utilisateurs.html',
  styleUrls: ['./utilisateurs.css']
})
export class Utilisateurs implements OnInit {
  users: any[] = [];
  filteredUsers: any[] = [];
  paginatedUsers: any[] = [];

  loading = false;

  selectedUser: any = null;
  showViewModal = false;
  showEditModal = false;

  searchTerm = '';
  filterRole = '';
  filterGroupe = '';

  sortColumn = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  currentPage = 1;
  pageSize = 8;
  totalPages = 1;

  editData: any = {
    id: null,
    nom: '',
    email: '',
    dateNaissance: ''
  };

  constructor(
    private utilisateursService: UtilisateursService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.getUsers();
  }

  get availableRoles(): string[] {
    return [...new Set(this.users.map(u => u.role?.name).filter(Boolean))];
  }

  get availableGroupes(): string[] {
    return [...new Set(this.users.map(u => u.groupe?.libelle).filter(Boolean))];
  }

  get adminCount(): number {
    return this.users.filter(u => u.role?.name?.toLowerCase() === 'admin').length;
  }

  get pageNumbers(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i + 1);
  }

  getUsers(): void {
    this.loading = true;
    this.cdr.detectChanges();

    this.utilisateursService.getAllUsers()
      .pipe(
        finalize(() => {
          this.loading = false;
          this.cdr.detectChanges();
        })
      )
      .subscribe({
        next: (response: any) => {
          this.users = this.extractUsers(response);

          this.filteredUsers = [...this.users];
          this.currentPage = 1;
          this.totalPages = Math.max(1, Math.ceil(this.filteredUsers.length / this.pageSize));
          this.updatePage();

          this.cdr.detectChanges();
        },
        error: (err: any) => {
          console.error('Erreur chargement utilisateurs :', err);

          this.users = [];
          this.filteredUsers = [];
          this.paginatedUsers = [];
          this.currentPage = 1;
          this.totalPages = 1;

          this.cdr.detectChanges();
        }
      });
  }

  private extractUsers(response: any): any[] {
    if (Array.isArray(response)) return response;
    if (Array.isArray(response?.data)) return response.data;
    if (Array.isArray(response?.users)) return response.users;
    if (Array.isArray(response?.content)) return response.content;
    if (Array.isArray(response?.results)) return response.results;

    return [];
  }

  applyFilters(): void {
    let result = [...this.users];

    if (this.searchTerm.trim()) {
      const term = this.searchTerm.toLowerCase().trim();

      result = result.filter(u =>
        u.nom?.toLowerCase().includes(term) ||
        u.email?.toLowerCase().includes(term)
      );
    }

    if (this.filterRole) {
      result = result.filter(u => u.role?.name === this.filterRole);
    }

    if (this.filterGroupe) {
      result = result.filter(u => u.groupe?.libelle === this.filterGroupe);
    }

    if (this.sortColumn) {
      result.sort((a, b) => {
        const valA = this.getNestedValue(a, this.sortColumn);
        const valB = this.getNestedValue(b, this.sortColumn);

        const cmp = String(valA ?? '').localeCompare(String(valB ?? ''));

        return this.sortDirection === 'asc' ? cmp : -cmp;
      });
    }

    this.filteredUsers = result;
    this.currentPage = 1;
    this.totalPages = Math.max(1, Math.ceil(this.filteredUsers.length / this.pageSize));
    this.updatePage();

    this.cdr.detectChanges();
  }

  getNestedValue(obj: any, key: string): any {
    return key.split('.').reduce((o, k) => o?.[k], obj);
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
    if (page < 1 || page > this.totalPages) return;

    this.currentPage = page;
    this.updatePage();
    this.cdr.detectChanges();
  }

  updatePage(): void {
    const start = (this.currentPage - 1) * this.pageSize;
    this.paginatedUsers = this.filteredUsers.slice(start, start + this.pageSize);
  }

  getInitials(nom: string): string {
    if (!nom) return '?';

    return nom
      .split(' ')
      .map(n => n[0])
      .slice(0, 2)
      .join('')
      .toUpperCase();
  }

  getRoleClass(role: string): string {
    if (!role) return '';

    const map: Record<string, string> = {
      Admin: 'role-admin',
      Utilisateur: 'role-user',
      Moderateur: 'role-mod'
    };

    return map[role] || 'role-default';
  }

  viewUser(user: any): void {
    this.selectedUser = user;
    this.showViewModal = true;
  }

  closeViewModal(): void {
    this.showViewModal = false;
    this.selectedUser = null;
  }

  openEditModal(user: any): void {
    this.editData = {
      id: user.id,
      nom: user.nom,
      email: user.email,
      dateNaissance: user.dateNaissance
    };

    this.showEditModal = true;
  }

  closeEditModal(): void {
    this.showEditModal = false;
  }

  updateUser(): void {
    const payload = {
      nom: this.editData.nom,
      email: this.editData.email,
      dateNaissance: this.editData.dateNaissance
    };

    this.utilisateursService.updateUser(this.editData.id, payload).subscribe({
      next: () => {
        this.closeEditModal();
        this.getUsers();
      },
      error: (err: any) => {
        console.error('Erreur modification :', err);
      }
    });
  }

  deleteUser(id: number): void {
    if (!confirm('Voulez-vous vraiment supprimer cet utilisateur ?')) return;

    this.utilisateursService.deleteUser(id).subscribe({
      next: () => {
        this.getUsers();
      },
      error: (err: any) => {
        console.error('Erreur suppression :', err);
      }
    });
  }
}