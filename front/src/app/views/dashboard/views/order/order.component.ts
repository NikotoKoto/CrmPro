import { Component } from '@angular/core';
import { OrdersListComponent } from "./components/orders-list.component";

@Component({
  selector: 'app-order',
  imports: [OrdersListComponent],
  template: `
   
      <h2 class="text-xxl text-bold  my-10">📦 Les commandes </h2>
      <app-orders-list/>
  `,
  styles: `
  :host{
    display:flex;
    flex-direction:column;
    align-items:center;
    gap: 50px;

  }`
})
export class OrderComponent {

}
