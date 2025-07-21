import { Component, inject, signal } from '@angular/core';
import { OrderService } from '../shared/service/order.service';
import { OrderFormComponent } from './order-form.component';

@Component({
  selector: 'app-orders-list',
  imports: [OrderFormComponent],
  template: `
    <div class="order-grid">
      @if(!isCollapse()){

      <button class="btn btn-primary btn-add" (click)="addOrder()">+</button>
      } @if(isCollapse()){
      <app-order-form class="order-form" (formSubmitted)="onFormSubmitted()" />
      }
    
    
      @for(order of orderList(); track $index){
      <div class="card">
        <ul>
          <li class="order-detail"><strong>Entreprise: </strong>{{ order.company }}</li>
          <li class="order-detail"><strong>Date: </strong>{{ order.orderDate }}</li>
          <li class="order-detail"><strong>Status: </strong>{{ order.orderStatus }}</li>
          <li class="order-detail"><strong>Commande: </strong> {{ order.orders }}</li>
          <li class="order-detail"><strong>Total: </strong>{{ order.totalAmount }} €</li>
        </ul>
      </div>
      }
    </div>
  `,
  styles: `
  :host{
    display:block;
    padding:1rem;
    width:100%;
  }

  .order-form{
    width:200px;
  }

  .btn-add{
    font-size:50px;
    width:200px;
    height: 350px;
  }
  .card{
    width:200px;
    height:350px;
  }

  .order-grid{
     display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 1rem;
  }

  .order-detail {
    display:flex;
    flex-direction:column;
  padding: 0.5rem;
  border-bottom: 1px solid #eee;
  font-size: 1.2rem;
  color: #333;
}
 `,
})
export class OrdersListComponent {
  private OrderService = inject(OrderService);
  orderList = this.OrderService.currentOrder;
  isCollapse = signal(false);

  addOrder() {
    this.isCollapse.set(true);
  }
  onFormSubmitted() {
    this.isCollapse.set(false);
  }
}
