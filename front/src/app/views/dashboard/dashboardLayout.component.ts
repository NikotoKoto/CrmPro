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
      grid-template-columns: 15% 85%;
      min-height: 100vh;
    }
    .main-content {
      flex: 1;
      padding: 20px;
    }
  `]
})
export class DashboardLayoutComponent {
  authService = inject(AuthService);
  currentUser = this.authService.currentUser;
}
