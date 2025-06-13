import { Routes } from '@angular/router';
import { DashboardLayoutComponent } from './dashboardLayout.component';

export const dashboardRoutes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    loadComponent: async () =>
      (await import('./views/dashboardHome/dashboard-home.component'))
        .DashboardHomeComponent,
  },
  {
    path: 'contacts',
    loadComponent: async () =>
      (await import('./views/contact/contacts.component')).ContactsComponent,
  },
  {
    path: 'order',
    loadComponent: async () =>
      (await import('./views/order.component')).OrderComponent,
  },
  {
    path: 'chiffres',
    loadComponent: async () =>
      (await import('./views/sales.component')).SalesComponent,
  },
  {
    path: 'documents',
    loadComponent: async () =>
      (await import('./views/docs.component')).DocsComponent,
  },
];
