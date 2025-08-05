import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import {
  LucideAngularModule,
  User,
  ShoppingCart,
  BarChart,
  FileText,
  MessageCircle,
  Store,
  Mail,
  CreditCard,
  Wrench,
  LayoutDashboard,
} from 'lucide-angular';

@Component({
  selector: 'app-sidebar',
  imports: [LucideAngularModule,RouterLink],
  template: `
    <div class="flex justify-center  mt-20 mb-20 ">
      <div class="sidebar-title flex items-center gap-16">
        <lucide-icon [img]="LayoutDashboard" class="icon" /><a class="dashboard-a" routerLink="/dashboard">Dashboard</a>
      </div>
    </div>
    <hr>
    <nav class="mt-20">
      <h3 class="flex justify-center mb-20 mt-20 sidebar-option">Management</h3>
      <ul class="flex flex-col items-center gap-20">
        <li><lucide-icon [img]="User" class="icon" /><a routerLink="/dashboard/contacts">Contact</a></li>
        <li><lucide-icon [img]="ShoppingCart" class="icon" /><a routerLink="/dashboard/order">Commandes</a></li>
        <li><lucide-icon [img]="BarChart" class="icon" /><a>Chiffres d'affaire</a></li>
        <li><lucide-icon [img]="FileText" class="icon" /><a>Documents</a></li>
      </ul>
      <hr />

      <h3 class="flex justify-center mb-20 mt-20 sidebar-option">Connexion</h3>
      <ul class="flex flex-col items-center gap-20">
        <li><lucide-icon [img]="MessageCircle" class="icon" /><a>Chat</a></li>
        <li><lucide-icon [img]="Store" class="icon" /><a>Marché</a></li>
        <li><lucide-icon [img]="Mail" class="icon" /><a>Email</a></li>
      </ul>
      <hr />

      <h3 class="flex justify-center mb-20 mt-20 sidebar-option">Client</h3>
      <ul class="flex flex-col items-center gap-20">
        <li><lucide-icon [img]="CreditCard" class="icon" /><a>Transaction</a></li>
        <li><lucide-icon [img]="Wrench" class="icon" /><a>Maintenance</a></li>
      </ul>
    </nav>
  `,
  styles: `
:host {
  display: flex;
  flex-direction: column;
  background-color: var(--dark-grey);
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  color: white;
  width: 250px;
  min-height: 100vh;
  z-index: 5;
}

.dashboard-a:hover {
  color: white;
}

li {
  display: flex;
  justify-content: center;
  color: var(--primary-hex);
  width: 100%;
  padding: 10px 0;
  border: 1px solid transparent;
}

li:hover {
  background-color: var(--white);
  color: var(--primary-hex);
  border: 1px solid var(--primary-hex);
  cursor: pointer;
}

hr {
  margin-top: 20px;
  border: 1px solid var(--grey);
  width: 100%;
}

.sidebar-title {
  font-size: 25px;
  padding: 15px;
  border-radius: 15px;
  background-color: var(--primary-hex);
  cursor: pointer;
}

.sidebar-option {
  color: var(--grey);
}

/* ✅ Responsive */
@media (max-width: 768px) {
  :host {
    flex-direction: row;
    width: 100%;
    min-height: auto;
    justify-content: space-around;
    align-items: center;
    padding: 10px;
    box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
  }

  li {
    padding: 10px;
    border: none;
    width: auto;
  }

  li:hover {
  background-color: var(--white);
  color: var(--primary-hex);
  border: 1px solid var(--primary-hex);
  cursor: pointer;
}

  .sidebar-title {
    display: none; /* cache le titre sur petit écran */
  }

  hr {
    display: none;
  }
}`,
})
export class SidebarComponent {
  readonly LayoutDashboard =LayoutDashboard;
  readonly User =User;
  readonly ShoppingCart =ShoppingCart;
  readonly FileText =FileText;
  readonly BarChart = BarChart;
  readonly MessageCircle =MessageCircle;
  readonly Store =Store;
  readonly Mail =Mail;
  readonly CreditCard =CreditCard;
  readonly Wrench =Wrench;



}
