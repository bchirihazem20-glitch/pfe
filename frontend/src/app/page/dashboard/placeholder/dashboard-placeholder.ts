import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard-placeholder',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="p-6 max-w-4xl">
      <h1 class="text-2xl font-semibold text-gray-800">{{ title }}</h1>
      <p class="text-gray-600 mt-2">Contenu à venir.</p>
    </div>
  `,
})
export class DashboardPlaceholder {
  readonly title: string;

  constructor(route: ActivatedRoute) {
    this.title = route.snapshot.data['title'] ?? 'Page';
  }
}
