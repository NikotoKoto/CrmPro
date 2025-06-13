import { Component, inject } from '@angular/core';
import { AuthService } from '../../../../shared/services/auth.service';
import { DashboardChartComponent } from "./components/dashboard-chart.component";

@Component({
  selector: 'app-dashboard-home',
  imports: [DashboardChartComponent],
  template: ` <h2 class="text-center">Bienvenue {{ user().firstname }} sur ton dashboard</h2>
  <div class="layout mt-20">
    <app-dashboard-chart/> 
    <div class="card flex flex-col">
      <p>Nombre de clients</p>
      <p>25000</p>
    </div>
    </div>
    `,
  styles: `
  :host{
    display:flex;
    flex-direction:column;
    
  }
  .layout{
    display:grid;
    align-items:center;
    padding:20px;
    gap:20px;
    grid-template-columns:repeat(3,1fr)
  }`,
})
export class DashboardHomeComponent {
  AuthService = inject(AuthService);
  user = this.AuthService.currentUser;
}
