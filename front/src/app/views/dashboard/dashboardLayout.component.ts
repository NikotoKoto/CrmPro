import { RouterOutlet } from '@angular/router';
import { Component, inject } from '@angular/core';
import { AuthService } from '../../shared/services/auth.service';
import { SidebarComponent } from '../../components/sidebar.component';

@Component({
  selector: 'app-dashboardLayout',
  imports: [SidebarComponent, RouterOutlet],
  template: `
    <div class="layout">
      <app-sidebar />
      <div class="main-content">
        <router-outlet />
      </div>
    </div>
  `,
  styles: [`
  .layout {
  display: grid;
  grid-template-columns: 250px 1fr;
  min-height: 100vh;
}

/* Contenu principal */
.main-content {
  padding: 20px;
  background-color: var(--background-color);
  overflow-y: auto;
}

/* Responsive pour mobile et tablette */
@media (max-width: 768px) {
  .layout {
    display: flex;
    flex-direction: row;
  }

  app-sidebar {
    width: 30%;
    border-bottom: 1px solid #e0e0e0;
  }

  .main-content {
    display:flex;
    align-items:center;
    justify-content:center;
    padding: 16px;
  }
}

  `]
})
export class DashboardLayoutComponent {
  authService = inject(AuthService);
  currentUser = this.authService.currentUser;
}
